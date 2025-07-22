package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dto.AttendanceAnomalyDTO;
import com.example.demo.dto.AttendanceRecordDTO;
import com.example.demo.entity.AttendanceRecords;
import com.example.demo.entity.Departments;
import com.example.demo.entity.Employees;
import com.example.demo.mapper.AttendanceRecordsMapper;
import com.example.demo.mapper.EmployeesMapper;
import com.example.demo.mapper.DepartmentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceRecordsMapper attendanceRecordsMapper;
    @Autowired
    private EmployeesMapper employeesMapper;
    @Autowired
    private DepartmentsMapper departmentsMapper;

    public List<AttendanceRecords> queryAttendanceRecords(String yearMonth, Integer empId) {
        LambdaQueryWrapper<AttendanceRecords> wrapper = new LambdaQueryWrapper<>();
        if (yearMonth != null && !yearMonth.isEmpty()) {
            wrapper.apply("to_char(attendance_date, 'YYYY-MM') = {0}", yearMonth);
        }
        if (empId != null) {
            wrapper.eq(AttendanceRecords::getEmpId, empId);
        }
        return attendanceRecordsMapper.selectList(wrapper);
    }

    public IPage<AttendanceRecords> queryAttendanceRecordsPage(String yearMonth, Integer empId, int pageNum, int pageSize) {
        LambdaQueryWrapper<AttendanceRecords> wrapper = new LambdaQueryWrapper<>();
        if (yearMonth != null && !yearMonth.isEmpty()) {
            wrapper.apply("to_char(attendance_date, 'YYYY-MM') = {0}", yearMonth);
        }
        if (empId != null) {
            wrapper.eq(AttendanceRecords::getEmpId, empId);
        }
        Page<AttendanceRecords> page = new Page<>(pageNum, pageSize);
        return attendanceRecordsMapper.selectPage(page, wrapper);
    }

    public List<AttendanceAnomalyDTO> queryAttendanceAnomalies(String yearMonth, String anomalyType, int threshold) {
        final int type;
        if ("迟到".equals(anomalyType)) type = 1;
        else if ("早退".equals(anomalyType)) type = 2;
        else if ("缺勤".equals(anomalyType)) type = 3;
        else type = 0;
        List<Employees> emps = employeesMapper.selectList(
            new LambdaQueryWrapper<Employees>().eq(Employees::getEmploymentStatus, 1)
        );
        List<AttendanceAnomalyDTO> result = new ArrayList<>();
        for (Employees emp : emps) {
            List<AttendanceRecords> records = attendanceRecordsMapper.selectList(
                new LambdaQueryWrapper<AttendanceRecords>()
                    .eq(AttendanceRecords::getEmpId, emp.getId())
                    .apply("to_char(attendance_date, 'YYYY-MM') = {0}", yearMonth)
            );
            List<AttendanceRecords> anomalies = records.stream().filter(r -> {
                if (type == 1) return r.getLateMinutes() != null && r.getLateMinutes() > 0;
                if (type == 2) return r.getEarlyLeaveMinutes() != null && r.getEarlyLeaveMinutes() > 0;
                if (type == 3) return r.getAbsenceType() != null && (r.getAbsenceType() == 1 || r.getAbsenceType() == 2);
                return false;
            }).collect(Collectors.toList());
            if (anomalies.size() >= threshold) {
                AttendanceAnomalyDTO dto = new AttendanceAnomalyDTO();
                dto.setEmpCode(emp.getEmpCode());
                dto.setEmpName(emp.getEmpName());
                dto.setYearMonth(yearMonth);
                dto.setAnomalyType(anomalyType);
                Departments dept = departmentsMapper.selectById(emp.getDeptId());
                if (dto.getClass().getDeclaredMethods() != null) {
                    try {
                        dto.getClass().getMethod("setDeptName", String.class).invoke(dto, dept != null ? dept.getDeptName() : null);
                    } catch (Exception ignored) {}
                }
                dto.setAnomalyCount(anomalies.size());
                result.add(dto);
            }
        }
        return result;
    }
}
