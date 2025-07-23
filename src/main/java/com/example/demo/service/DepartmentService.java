package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.entity.Departments;
import com.example.demo.entity.Employees;
import com.example.demo.entity.AttendanceRecords;
import com.example.demo.dto.DepartmentOverviewDTO;
import com.example.demo.dto.EmployeeBasicInfoDTO;
import com.example.demo.mapper.DepartmentsMapper;
import com.example.demo.mapper.EmployeesMapper;
import com.example.demo.mapper.AttendanceRecordsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentsMapper departmentsMapper;
    @Autowired
    private EmployeesMapper employeesMapper;
    @Autowired
    private AttendanceRecordsMapper attendanceRecordsMapper;

    // 部门员工统计
    public List<DepartmentOverviewDTO> queryDepartmentStats(String yearMonth) {
        List<Departments> depts = departmentsMapper.selectList(null);
        return depts.stream().map(dept -> {
            DepartmentOverviewDTO dto = new DepartmentOverviewDTO();
            dto.setDeptName(dept.getDeptName());
            dto.setDeptId(dept.getId());
            // 查询在职员工
            List<Employees> emps = employeesMapper.selectList(
                new LambdaQueryWrapper<Employees>()
                    .eq(Employees::getDeptId, dept.getId())
                    .eq(Employees::getEmploymentStatus, 1)
            );
            dto.setEmployeeCount(emps.size());
            // 薪资统计
            List<Double> salaries = emps.stream()
                .filter(e -> e.getBaseSalary() != null)
                .map(e -> e.getBaseSalary().doubleValue())
                .collect(Collectors.toList());
            dto.setAvgSalary(salaries.isEmpty() ? 0.0 : salaries.stream().mapToDouble(Double::doubleValue).average().orElse(0.0));
            dto.setMaxSalary(salaries.isEmpty() ? 0.0 : salaries.stream().mapToDouble(Double::doubleValue).max().orElse(0.0));
            dto.setMinSalary(salaries.isEmpty() ? 0.0 : salaries.stream().mapToDouble(Double::doubleValue).min().orElse(0.0));
            return dto;
        }).collect(Collectors.toList());
    }

    // 部门层级结构统计
    public List<DepartmentOverviewDTO> queryDepartmentHierarchyStats() {
        List<Departments> depts = departmentsMapper.selectList(null);
        return depts.stream().map(dept -> {
            DepartmentOverviewDTO dto = new DepartmentOverviewDTO();
            dto.setDeptName(dept.getDeptName());
            dto.setDeptId(dept.getId());
            dto.setLevel(dept.getLevel());
            List<Employees> emps = employeesMapper.selectList(
                new LambdaQueryWrapper<Employees>()
                    .eq(Employees::getDeptId, dept.getId())
                    .eq(Employees::getEmploymentStatus, 1)
            );
            dto.setEmployeeCount(emps.size());
            return dto;
        }).collect(Collectors.toList());
    }

    // 部门考勤异常统计
    public List<DepartmentOverviewDTO> queryDepartmentAnomalyStats(String yearMonth, int threshold) {
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
            dto.setTotalEmployees(emps.size());
            // 统计异常员工
            long anomalyEmployees = emps.stream().filter(emp -> {
                List<AttendanceRecords> records = attendanceRecordsMapper.selectList(
                    new LambdaQueryWrapper<AttendanceRecords>()
                        .eq(AttendanceRecords::getEmpId, emp.getId())
                        .apply("to_char(attendance_date, 'YYYY-MM') = {0}", yearMonth)
                );
                long anomalyCount = records.stream().filter(r ->
                        (r.getLateMinutes() != null && r.getLateMinutes() > 0) ||
                        (r.getEarlyLeaveMinutes() != null && r.getEarlyLeaveMinutes() > 0) ||
                        (r.getAbsenceType() != null && (r.getAbsenceType() == 1 || r.getAbsenceType() == 2)) ||
                        (r.getClockInTime() == null || r.getClockOutTime() == null)
                ).count();
                return anomalyCount >= threshold;
            }).count();
            dto.setAnomalyEmployees((int) anomalyEmployees);
            dto.setAnomalyRate(emps.size() > 0 ? (double) anomalyEmployees / emps.size() * 100 : 0.0);
            return dto;
        }).filter(dto -> dto.getAnomalyEmployees() > 0).collect(Collectors.toList());
    }

    // 查询指定部门的所有在职员工基本信息
    public List<EmployeeBasicInfoDTO> queryDepartmentEmployees(int deptId) {
        List<Employees> emps = employeesMapper.selectList(
            new LambdaQueryWrapper<Employees>()
                .eq(Employees::getDeptId, deptId)
                .eq(Employees::getEmploymentStatus, 1)
        );
        return emps.stream().map(emp -> {
            EmployeeBasicInfoDTO dto = new EmployeeBasicInfoDTO();
            dto.setEmpCode(emp.getEmpCode());
            dto.setEmpName(emp.getEmpName());
            dto.setPosition(emp.getPosition());
            dto.setHireDate(emp.getHireDate() != null ? emp.getHireDate().toString() : "");
            dto.setGender(emp.getGender());
            dto.setPhone(emp.getPhone() != null ? emp.getPhone() : "");
            dto.setEmail(emp.getEmail() != null ? emp.getEmail() : "");
            dto.setLevel(emp.getLevel() != null ? emp.getLevel() : "");
            dto.setContractType(emp.getContractType() != null ? emp.getContractType().toString() : "");
            dto.setEmploymentStatus(emp.getEmploymentStatus() != null ? emp.getEmploymentStatus().toString() : "");
            dto.setBaseSalary(emp.getBaseSalary() != null ? emp.getBaseSalary().doubleValue() : null);
            dto.setBirthDate(emp.getBirthDate() != null ? emp.getBirthDate().toString() : "");
            // 查询部门名和编码
            Departments dept = departmentsMapper.selectById(emp.getDeptId());
            if (dept != null) {
                dto.setDeptName(dept.getDeptName());
                dto.setDeptCode(dept.getDeptCode());
            }
            return dto;
        }).collect(Collectors.toList());
    }
}