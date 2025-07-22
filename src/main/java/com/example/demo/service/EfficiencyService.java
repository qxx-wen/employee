package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.entity.AttendanceRecords;
import com.example.demo.entity.Employees;
import com.example.demo.entity.Departments;
import com.example.demo.dto.EmployeeEfficiencyDTO;
import com.example.demo.dto.DepartmentOverviewDTO;
import com.example.demo.mapper.AttendanceRecordsMapper;
import com.example.demo.mapper.EmployeesMapper;
import com.example.demo.mapper.DepartmentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EfficiencyService {
    @Autowired
    private AttendanceRecordsMapper attendanceRecordsMapper;
    @Autowired
    private EmployeesMapper employeesMapper;
    @Autowired
    private DepartmentsMapper departmentsMapper;

    // 员工效率分析
    public EmployeeEfficiencyDTO analyzeEmployeeEfficiency(String empCode, String yearMonth, double standardWorkHoursPerDay, double overtimeRate) {
        Employees emp = employeesMapper.selectOne(new LambdaQueryWrapper<Employees>()
                .eq(Employees::getEmpCode, empCode)
                .eq(Employees::getEmploymentStatus, 1));
        if (emp == null) return null;
        Departments dept = departmentsMapper.selectById(emp.getDeptId());
        EmployeeEfficiencyDTO dto = new EmployeeEfficiencyDTO();
        dto.setEmpCode(empCode);
        dto.setEmpName(emp.getEmpName());
        dto.setDeptName(dept != null ? dept.getDeptName() : null);
        dto.setBaseSalary(emp.getBaseSalary() != null ? emp.getBaseSalary().doubleValue() : 0.0);

        // 查询考勤数据
        List<AttendanceRecords> records = attendanceRecordsMapper.selectList(
                new LambdaQueryWrapper<AttendanceRecords>()
                        .eq(AttendanceRecords::getEmpId, emp.getId())
                        .apply("to_char(attendance_date, 'YYYY-MM') = {0}", yearMonth)
        );
        int totalWorkDays = records.size();
        int lateDays = (int) records.stream().filter(r -> r.getLateMinutes() != null && r.getLateMinutes() > 0).count();
        int earlyLeaveDays = (int) records.stream().filter(r -> r.getEarlyLeaveMinutes() != null && r.getEarlyLeaveMinutes() > 0).count();
        int absentDays = (int) records.stream().filter(r -> r.getAbsenceType() != null && r.getAbsenceType() == 2).count();
        double totalWorkHours = records.stream().mapToDouble(r -> r.getWorkHours() != null ? r.getWorkHours().doubleValue() : 0.0).sum();
        double overtimeHours = records.stream().mapToDouble(r -> r.getOvertimeHours() != null ? r.getOvertimeHours().doubleValue() : 0.0).sum();
        double avgWorkHoursPerDay = totalWorkDays > 0 ? totalWorkHours / totalWorkDays : 0.0;
        double standardWorkHours = totalWorkDays * standardWorkHoursPerDay;
        double efficiencyRate = standardWorkHours > 0 ? (totalWorkHours / standardWorkHours) * 100 : 0.0;
        double overtimeRateValue = totalWorkHours > 0 ? (overtimeHours / totalWorkHours) * 100 : 0.0;
        double baseSalary = dto.getBaseSalary() != null ? dto.getBaseSalary() : 0.0;
        double hourlyRate = standardWorkHoursPerDay > 0 ? baseSalary / (standardWorkHoursPerDay * 30) : 0.0;
        double overtimePay = overtimeHours * hourlyRate * overtimeRate;
        double totalPay = baseSalary + overtimePay;

        dto.setTotalWorkDays(totalWorkDays);
        dto.setStandardWorkHours(standardWorkHours);
        dto.setActualWorkHours(totalWorkHours);
        dto.setOvertimeHours(overtimeHours);
        dto.setEfficiencyRate(efficiencyRate);
        dto.setOvertimeRate(overtimeRateValue);
        dto.setOvertimePay(overtimePay);
        dto.setTotalPay(totalPay);
        dto.setLateDays(lateDays);
        dto.setEarlyLeaveDays(earlyLeaveDays);
        dto.setAbsentDays(absentDays);
        dto.setAverageWorkHoursPerDay(avgWorkHoursPerDay);
        return dto;
    }

    // 部门效率对比
    public List<DepartmentOverviewDTO> generateDepartmentEfficiencyComparison(String yearMonth, double standardWorkHoursPerDay) {
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
            double standardWorkHours = totalWorkDays * standardWorkHoursPerDay;
            double efficiencyRate = standardWorkHours > 0 ? (totalWorkHours / standardWorkHours) * 100 : 0.0;
            double overtimeRateValue = totalWorkHours > 0 ? (overtimeHours / totalWorkHours) * 100 : 0.0;
            dto.setTotalWorkHours(totalWorkHours);
            dto.setTotalOvertimeHours(overtimeHours);
            dto.setAvgWorkHoursPerDay(avgWorkHoursPerDay);
            dto.setEfficiencyRate(efficiencyRate);
            dto.setOvertimeRate(overtimeRateValue);
            return dto;
        }).collect(Collectors.toList());
    }
}
