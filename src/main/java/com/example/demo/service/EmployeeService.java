package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.demo.entity.Employees;
import com.example.demo.entity.EmploymentRecords;
import com.example.demo.entity.SalaryRecords;
import com.example.demo.entity.AttendanceRecords;
import com.example.demo.entity.Departments;
import com.example.demo.dto.EmployeeComprehensiveDTO;
import com.example.demo.dto.EmployeeBasicInfoDTO;
import com.example.demo.dto.AttendanceStatisticsDTO;
import com.example.demo.dto.SalaryDetailDTO;
import com.example.demo.dto.PerformanceRatingDTO;
import com.example.demo.dto.EmployeeHistoryDTO;
import com.example.demo.mapper.EmployeesMapper;
import com.example.demo.mapper.EmploymentRecordsMapper;
import com.example.demo.mapper.SalaryRecordsMapper;
import com.example.demo.mapper.AttendanceRecordsMapper;
import com.example.demo.mapper.DepartmentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private EmployeesMapper employeesMapper;
    @Autowired
    private EmploymentRecordsMapper employmentRecordsMapper;
    @Autowired
    private SalaryRecordsMapper salaryRecordsMapper;
    @Autowired
    private AttendanceRecordsMapper attendanceRecordsMapper;
    @Autowired
    private DepartmentsMapper departmentsMapper;

    public EmployeeComprehensiveDTO queryEmployeeComprehensiveInfo(String empCode, String yearMonth) {
        EmployeeComprehensiveDTO result = new EmployeeComprehensiveDTO();
        result.setBasicInfo(queryEmployeeBasicInfo(empCode));
        result.setAttendanceStats(queryAttendanceStatistics(empCode, yearMonth));
        result.setSalaryDetail(querySalaryDetail(empCode, yearMonth));
        result.setPerformanceRating(calculatePerformanceRating(result.getAttendanceStats()));
        return result;
    }

    public EmployeeBasicInfoDTO queryEmployeeBasicInfo(String empCode) {
        Employees emp = employeesMapper.selectOne(Wrappers.<Employees>lambdaQuery().eq(Employees::getEmpCode, empCode));
        if (emp == null) return null;
        EmployeeBasicInfoDTO dto = new EmployeeBasicInfoDTO();
        dto.setEmpCode(emp.getEmpCode());
        dto.setEmpName(emp.getEmpName());
        dto.setGender(emp.getGender());
        dto.setBirthDate(emp.getBirthDate() != null ? emp.getBirthDate().toString() : null);
        dto.setPhone(emp.getPhone());
        dto.setEmail(emp.getEmail());
        dto.setPosition(emp.getPosition());
        dto.setLevel(emp.getLevel());
        dto.setHireDate(emp.getHireDate() != null ? emp.getHireDate().toString() : null);
        dto.setContractType(emp.getContractType() != null ? emp.getContractType().toString() : null);
        dto.setEmploymentStatus(emp.getEmploymentStatus() != null ? emp.getEmploymentStatus().toString() : null);
        dto.setBaseSalary(emp.getBaseSalary() != null ? emp.getBaseSalary().doubleValue() : null);

        // 查询部门名称和编码
        if (emp.getDeptId() != null) {
            Departments dept = departmentsMapper.selectById(emp.getDeptId());
            if (dept != null) {
                dto.setDeptName(dept.getDeptName());
                dto.setDeptCode(dept.getDeptCode());
            }
        }
        return dto;
    }

    private AttendanceStatisticsDTO queryAttendanceStatistics(String empCode, String yearMonth) {
        Employees emp = employeesMapper.selectOne(Wrappers.<Employees>lambdaQuery().eq(Employees::getEmpCode, empCode));
        if (emp == null) return new AttendanceStatisticsDTO();
        LambdaQueryWrapper<AttendanceRecords> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AttendanceRecords::getEmpId, emp.getId())
               .apply("to_char(attendance_date, 'YYYY-MM') = {0}", yearMonth);
        List<AttendanceRecords> records = attendanceRecordsMapper.selectList(wrapper);
        AttendanceStatisticsDTO dto = new AttendanceStatisticsDTO();
        int totalDays = records.size();
        int completeDays = (int) records.stream().filter(r -> r.getClockInTime() != null && r.getClockOutTime() != null).count();
        int lateDays = (int) records.stream().filter(r -> r.getLateMinutes() != null && r.getLateMinutes() > 0).count();
        int earlyLeaveDays = (int) records.stream().filter(r -> r.getEarlyLeaveMinutes() != null && r.getEarlyLeaveMinutes() > 0).count();
        int absenceDays = (int) records.stream().filter(r -> r.getAbsenceType() != null && (r.getAbsenceType() == 1 || r.getAbsenceType() == 2)).count();
        double totalWorkHours = records.stream().mapToDouble(r -> r.getWorkHours() != null ? r.getWorkHours().doubleValue() : 0.0).sum();
        double totalOvertimeHours = records.stream().mapToDouble(r -> r.getOvertimeHours() != null ? r.getOvertimeHours().doubleValue() : 0.0).sum();
        int totalLateMinutes = records.stream().mapToInt(r -> r.getLateMinutes() != null ? r.getLateMinutes() : 0).sum();
        int totalEarlyLeaveMinutes = records.stream().mapToInt(r -> r.getEarlyLeaveMinutes() != null ? r.getEarlyLeaveMinutes() : 0).sum();
        dto.setTotalDays(totalDays);
        dto.setCompleteDays(completeDays);
        dto.setLateDays(lateDays);
        dto.setEarlyLeaveDays(earlyLeaveDays);
        dto.setAbsenceDays(absenceDays);
        dto.setTotalWorkHours(totalWorkHours);
        dto.setTotalOvertimeHours(totalOvertimeHours);
        dto.setTotalLateMinutes(totalLateMinutes);
        dto.setTotalEarlyLeaveMinutes(totalEarlyLeaveMinutes);
        dto.setAttendanceRate(totalDays > 0 ? (double) completeDays / totalDays * 100 : 0.0);
        return dto;
    }

    private SalaryDetailDTO querySalaryDetail(String empCode, String yearMonth) {
        Employees emp = employeesMapper.selectOne(Wrappers.<Employees>lambdaQuery().eq(Employees::getEmpCode, empCode));
        if (emp == null) return null;
        SalaryRecords sr = salaryRecordsMapper.selectOne(Wrappers.<SalaryRecords>lambdaQuery()
                .eq(SalaryRecords::getEmpId, emp.getId())
                .eq(SalaryRecords::getSalaryMonth, yearMonth));
        if (sr == null) return null;
        SalaryDetailDTO dto = new SalaryDetailDTO();
        dto.setSalaryMonth(sr.getSalaryMonth());
        dto.setBaseSalary(sr.getBaseSalary() != null ? sr.getBaseSalary().doubleValue() : null);
        dto.setOvertimePay(sr.getOvertimePay() != null ? sr.getOvertimePay().doubleValue() : null);
        dto.setBonus(sr.getBonus() != null ? sr.getBonus().doubleValue() : null);
        dto.setAllowance(sr.getAllowance() != null ? sr.getAllowance().doubleValue() : null);
        dto.setDeduction(sr.getDeduction() != null ? sr.getDeduction().doubleValue() : null);
        dto.setSocialInsurance(sr.getSocialInsurance() != null ? sr.getSocialInsurance().doubleValue() : null);
        dto.setHousingFund(sr.getHousingFund() != null ? sr.getHousingFund().doubleValue() : null);
        dto.setTax(sr.getTax() != null ? sr.getTax().doubleValue() : null);
        dto.setNetSalary(sr.getNetSalary() != null ? sr.getNetSalary().doubleValue() : null);
        dto.setPayStatus(sr.getPayStatus() != null ? sr.getPayStatus().toString() : null);
        dto.setPayDate(sr.getPayDate() != null ? sr.getPayDate().toString() : null);
        dto.setRemarks(sr.getRemarks());
        return dto;
    }

    private PerformanceRatingDTO calculatePerformanceRating(AttendanceStatisticsDTO stats) {
        int totalDays = stats.getTotalDays();
        int lateDays = stats.getLateDays();
        int earlyLeaveDays = stats.getEarlyLeaveDays();
        int absenceDays = stats.getAbsenceDays();
        int completeDays = stats.getCompleteDays();
        double attendanceRate = totalDays > 0 ? (double) completeDays / totalDays : 0.0;
        double score = 100;
        score -= lateDays * 2;
        score -= earlyLeaveDays * 2;
        score -= absenceDays * 10;
        if (attendanceRate < 0.9) score -= 5;
        if (lateDays == 0 && earlyLeaveDays == 0 && absenceDays == 0) score += 5;
        String ratingLevel;
        if (score >= 90) ratingLevel = "A";
        else if (score >= 80) ratingLevel = "B";
        else if (score >= 70) ratingLevel = "C";
        else ratingLevel = "D";
        PerformanceRatingDTO dto = new PerformanceRatingDTO();
        dto.setRatingLevel(ratingLevel);
        dto.setRatingScore(score);
        dto.setEvaluationDate(java.time.LocalDate.now().toString());
        dto.setEvaluator("系统自动评定");
        dto.setComments("根据考勤自动评分");
        return dto;
    }

    // 员工历史记录
    public List<EmployeeHistoryDTO> queryEmployeeHistory(String empCode) {
        Employees emp = employeesMapper.selectOne(Wrappers.<Employees>lambdaQuery().eq(Employees::getEmpCode, empCode));
        if (emp == null) return List.of();
        List<EmploymentRecords> records = employmentRecordsMapper.selectList(Wrappers.<EmploymentRecords>lambdaQuery()
                .eq(EmploymentRecords::getEmpId, emp.getId())
                .orderByDesc(EmploymentRecords::getEffectiveDate));
        return records.stream().map(er -> {
            EmployeeHistoryDTO dto = new EmployeeHistoryDTO();
            dto.setRecordType(er.getRecordType() != null ? er.getRecordType().toString() : null);
            dto.setEffectiveDate(er.getEffectiveDate() != null ? er.getEffectiveDate().toString() : null);
            dto.setReason(er.getReason());
            dto.setPosition(er.getPosition());
            dto.setSalary(er.getSalary() != null ? er.getSalary().doubleValue() : null);
            dto.setRemarks(er.getRemarks());
            return dto;
        }).collect(Collectors.toList());
    }
}
