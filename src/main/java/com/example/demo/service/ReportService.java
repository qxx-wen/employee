package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.entity.Departments;
import com.example.demo.entity.Employees;
import com.example.demo.entity.AttendanceRecords;
import com.example.demo.dto.DepartmentAttendanceReportDTO;
import com.example.demo.dto.AttendanceStatisticsDTO;
import com.example.demo.dto.EmployeeAttendanceDetailDTO;
import com.example.demo.dto.DepartmentOverviewDTO;
import com.example.demo.mapper.DepartmentsMapper;
import com.example.demo.mapper.EmployeesMapper;
import com.example.demo.mapper.AttendanceRecordsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {
    @Autowired
    private DepartmentsMapper departmentsMapper;
    @Autowired
    private EmployeesMapper employeesMapper;
    @Autowired
    private AttendanceRecordsMapper attendanceRecordsMapper;

    // 生成部门考勤报告
    public DepartmentAttendanceReportDTO generateDepartmentAttendanceReport(int deptId, String yearMonth) {
        DepartmentAttendanceReportDTO dto = new DepartmentAttendanceReportDTO();
        Departments dept = departmentsMapper.selectById(deptId);
        dto.setDeptName(dept != null ? dept.getDeptName() : null);
        dto.setDeptId(deptId);
        dto.setTotalEmployees(getDepartmentEmployeeCount(deptId));
        dto.setAttendanceStats(getDepartmentAttendanceStats(deptId, yearMonth));
        dto.setEmployeeAttendanceList(getEmployeeAttendanceList(deptId, yearMonth));
        return dto;
    }

    // 获取部门员工数量
    private int getDepartmentEmployeeCount(int deptId) {
        return employeesMapper.selectCount(new LambdaQueryWrapper<Employees>()
                .eq(Employees::getDeptId, deptId)
                .eq(Employees::getEmploymentStatus, 1)).intValue();
    }

    // 获取部门考勤统计
    private AttendanceStatisticsDTO getDepartmentAttendanceStats(int deptId, String yearMonth) {
        List<Employees> emps = employeesMapper.selectList(new LambdaQueryWrapper<Employees>()
                .eq(Employees::getDeptId, deptId)
                .eq(Employees::getEmploymentStatus, 1));
        List<Integer> empIds = emps.stream().map(Employees::getId).collect(Collectors.toList());
        if (empIds.isEmpty()) return new AttendanceStatisticsDTO();
        List<AttendanceRecords> records = attendanceRecordsMapper.selectList(new LambdaQueryWrapper<AttendanceRecords>()
                .in(AttendanceRecords::getEmpId, empIds)
                .apply("to_char(attendance_date, 'YYYY-MM') = {0}", yearMonth));
        AttendanceStatisticsDTO dto = new AttendanceStatisticsDTO();
        int totalDays = records.size();
        int completeDays = (int) records.stream().filter(r -> r.getClockInTime() != null && r.getClockOutTime() != null).count();
        int lateDays = (int) records.stream().filter(r -> r.getLateMinutes() != null && r.getLateMinutes() > 0).count();
        int earlyLeaveDays = (int) records.stream().filter(r -> r.getEarlyLeaveMinutes() != null && r.getEarlyLeaveMinutes() > 0).count();
        int absenceDays = (int) records.stream().filter(r -> r.getAbsenceType() != null && (r.getAbsenceType() == 1 || r.getAbsenceType() == 2)).count();
        int leaveDays = (int) records.stream().filter(r -> r.getAbsenceType() != null && r.getAbsenceType() == 3).count();
        double totalWorkHours = records.stream().mapToDouble(r -> r.getWorkHours() != null ? r.getWorkHours().doubleValue() : 0.0).sum();
        double totalOvertimeHours = records.stream().mapToDouble(r -> r.getOvertimeHours() != null ? r.getOvertimeHours().doubleValue() : 0.0).sum();
        int totalLateMinutes = records.stream().mapToInt(r -> r.getLateMinutes() != null ? r.getLateMinutes() : 0).sum();
        int totalEarlyLeaveMinutes = records.stream().mapToInt(r -> r.getEarlyLeaveMinutes() != null ? r.getEarlyLeaveMinutes() : 0).sum();
        double averageWorkHours = completeDays > 0 ? totalWorkHours / completeDays : 0.0;
        double lateRate = completeDays > 0 ? (double) lateDays / completeDays * 100 : 0.0;
        double attendanceRate = totalDays > 0 ? (double) completeDays / totalDays * 100 : 0.0;
        dto.setTotalDays(totalDays);
        dto.setCompleteDays(completeDays);
        dto.setLateDays(lateDays);
        dto.setEarlyLeaveDays(earlyLeaveDays);
        dto.setAbsenceDays(absenceDays);
        dto.setLeaveDays(leaveDays);
        dto.setTotalWorkHours(totalWorkHours);
        dto.setTotalOvertimeHours(totalOvertimeHours);
        dto.setTotalLateMinutes(totalLateMinutes);
        dto.setTotalEarlyLeaveMinutes(totalEarlyLeaveMinutes);
        dto.setAttendanceRate(attendanceRate);
        dto.setAverageWorkHours(averageWorkHours);
        dto.setLateRate(lateRate);
        return dto;
    }

    // 获取员工考勤明细
    private List<EmployeeAttendanceDetailDTO> getEmployeeAttendanceList(int deptId, String yearMonth) {
        List<Employees> emps = employeesMapper.selectList(new LambdaQueryWrapper<Employees>()
                .eq(Employees::getDeptId, deptId)
                .eq(Employees::getEmploymentStatus, 1));
        List<Integer> empIds = emps.stream().map(Employees::getId).collect(Collectors.toList());
        if (empIds.isEmpty()) return List.of();
        List<AttendanceRecords> records = attendanceRecordsMapper.selectList(new LambdaQueryWrapper<AttendanceRecords>()
                .in(AttendanceRecords::getEmpId, empIds)
                .apply("to_char(attendance_date, 'YYYY-MM') = {0}", yearMonth)
                .orderByAsc(AttendanceRecords::getEmpId, AttendanceRecords::getAttendanceDate));
        return records.stream().map(r -> {
            Employees emp = emps.stream().filter(e -> e.getId().equals(r.getEmpId())).findFirst().orElse(null);
            EmployeeAttendanceDetailDTO dto = new EmployeeAttendanceDetailDTO();
            dto.setEmpCode(emp != null ? emp.getEmpCode() : null);
            dto.setEmpName(emp != null ? emp.getEmpName() : null);
            dto.setAttendanceDate(r.getAttendanceDate() != null ? r.getAttendanceDate().toString() : null);
            dto.setCheckInTime(r.getClockInTime() != null ? r.getClockInTime().toString() : null);
            dto.setCheckOutTime(r.getClockOutTime() != null ? r.getClockOutTime().toString() : null);
            dto.setLateMinutes(r.getLateMinutes());
            dto.setEarlyLeaveMinutes(r.getEarlyLeaveMinutes());
            dto.setAbsenceType(r.getAbsenceType());
            dto.setWorkHours(r.getWorkHours() != null ? r.getWorkHours().doubleValue() : null);
            dto.setRemarks(r.getRemarks());
            return dto;
        }).collect(Collectors.toList());
    }

    // 生成部门效率对比报告
    public List<DepartmentOverviewDTO> generateDepartmentEfficiencyComparison(String yearMonth) {
        List<Departments> depts = departmentsMapper.selectList(null);
        return depts.stream().map(dept -> {
            DepartmentOverviewDTO dto = new DepartmentOverviewDTO();
            dto.setDeptName(dept.getDeptName());
            dto.setDeptId(dept.getId());
            List<Employees> emps = employeesMapper.selectList(
                    new LambdaQueryWrapper<Employees>()
                            .eq(Employees::getDeptId, dept.getId())
                            .eq(Employees::getEmploymentStatus, 1)
            );
            dto.setEmployeeCount(emps.size());
            List<Integer> empIds = emps.stream().map(Employees::getId).collect(Collectors.toList());
            List<AttendanceRecords> records = empIds.isEmpty() ? List.of() : attendanceRecordsMapper.selectList(
                    new LambdaQueryWrapper<AttendanceRecords>()
                            .in(AttendanceRecords::getEmpId, empIds)
                            .apply("to_char(attendance_date, 'YYYY-MM') = {0}", yearMonth)
            );
            int totalWorkDays = records.size();
            double totalWorkHours = records.stream().mapToDouble(r -> r.getWorkHours() != null ? r.getWorkHours().doubleValue() : 0.0).sum();
            double overtimeHours = records.stream().mapToDouble(r -> r.getOvertimeHours() != null ? r.getOvertimeHours().doubleValue() : 0.0).sum();
            double avgWorkHoursPerDay = totalWorkDays > 0 ? totalWorkHours / totalWorkDays : 0.0;
            dto.setTotalWorkHours(totalWorkHours);
            dto.setTotalOvertimeHours(overtimeHours);
            dto.setAvgWorkHoursPerDay(avgWorkHoursPerDay);
            return dto;
        }).collect(Collectors.toList());
    }
}
