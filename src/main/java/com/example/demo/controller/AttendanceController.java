package com.example.demo.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.demo.dto.AttendanceRecordDTO;
import com.example.demo.entity.AttendanceRecords;
import com.example.demo.entity.Employees;
import com.example.demo.mapper.EmployeesMapper;
import com.example.demo.service.AttendanceService;
import com.example.demo.dto.AttendanceAnomalyDTO;
import com.example.demo.entity.Departments;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Tag(name = "考勤接口")
@RestController
@RequestMapping("/api/attendance")  // 场景一：考勤管理 - 考勤记录查询
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private EmployeesMapper employeesMapper;
    @Autowired
    private com.example.demo.mapper.AttendanceRecordsMapper attendanceRecordsMapper;
    @Autowired
    private com.example.demo.mapper.DepartmentsMapper departmentsMapper;

    @Operation(summary = "查询考勤记录")
    @GetMapping("/records")
    public List<AttendanceRecordDTO> getAttendanceRecords(
            @RequestParam String yearMonth,
            @RequestParam(required = false) Integer empId) {
        return attendanceService.queryAttendanceRecords(yearMonth, empId)
            .stream().map(r -> {
                AttendanceRecordDTO dto = new AttendanceRecordDTO();
                Employees emp = employeesMapper.selectById(r.getEmpId());
                dto.setEmpCode(emp != null ? emp.getEmpCode() : null);
                dto.setEmpName(emp != null ? emp.getEmpName() : null);
                dto.setAttendanceDate(r.getAttendanceDate() != null ? r.getAttendanceDate().toString() : null);
                dto.setCheckInTime(r.getClockInTime() != null ? r.getClockInTime().toString() : null);
                dto.setCheckOutTime(r.getClockOutTime() != null ? r.getClockOutTime().toString() : null);
                dto.setWorkHours(r.getWorkHours() != null ? r.getWorkHours().doubleValue() : null);
                dto.setLateMinutes(r.getLateMinutes());
                dto.setEarlyLeaveMinutes(r.getEarlyLeaveMinutes());
                dto.setAbsenceType(r.getAbsenceType());
                return dto;
            }).toList();
    }

    @Operation(summary = "考勤异常员工查询")
    @GetMapping("/anomalies")  // 场景一：考勤管理 - 考勤异常员工查询
    public List<AttendanceAnomalyDTO> getAttendanceAnomalies(
            @RequestParam String yearMonth,
            @RequestParam String anomalyType,
            @RequestParam Integer threshold) {
        return attendanceService.queryAttendanceAnomalies(yearMonth, anomalyType, threshold);
    }
}
