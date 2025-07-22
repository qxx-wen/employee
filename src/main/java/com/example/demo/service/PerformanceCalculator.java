package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.entity.AttendanceRecords;
import com.example.demo.entity.Employees;
import com.example.demo.mapper.AttendanceRecordsMapper;
import com.example.demo.mapper.EmployeesMapper;
import com.example.demo.dto.AttendanceRecordDTO;
import com.example.demo.dto.PerformanceRatingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerformanceCalculator {
    @Autowired
    private AttendanceRecordsMapper attendanceRecordsMapper;
    @Autowired
    private EmployeesMapper employeesMapper;

    // 1. 查询某员工某月所有考勤记录
    public List<AttendanceRecordDTO> getAttendanceRecords(String empCode, String yearMonth) {
        Employees emp = employeesMapper.selectOne(new LambdaQueryWrapper<Employees>()
                .eq(Employees::getEmpCode, empCode));
        if (emp == null) return List.of();
        LambdaQueryWrapper<AttendanceRecords> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AttendanceRecords::getEmpId, emp.getId());
        if (yearMonth != null && !yearMonth.isEmpty()) {
            wrapper.apply("to_char(attendance_date, 'YYYY-MM') = {0}", yearMonth);
        }
        List<AttendanceRecords> records = attendanceRecordsMapper.selectList(wrapper);
        return records.stream().map(r -> {
            AttendanceRecordDTO dto = new AttendanceRecordDTO();
            dto.setEmpCode(emp.getEmpCode());
            dto.setEmpName(emp.getEmpName());
            dto.setAttendanceDate(r.getAttendanceDate() != null ? r.getAttendanceDate().toString() : null);
            dto.setCheckInTime(r.getClockInTime() != null ? r.getClockInTime().toString() : null);
            dto.setCheckOutTime(r.getClockOutTime() != null ? r.getClockOutTime().toString() : null);
            dto.setWorkHours(r.getWorkHours() != null ? r.getWorkHours().doubleValue() : null);
            dto.setLateMinutes(r.getLateMinutes());
            dto.setEarlyLeaveMinutes(r.getEarlyLeaveMinutes());
            dto.setAbsenceType(r.getAbsenceType());
            return dto;
        }).collect(Collectors.toList());
    }

    // 2. 计算绩效
    public PerformanceRatingDTO calculatePerformanceRating(List<AttendanceRecordDTO> records, String evaluator) {
        int totalDays = records.size();
        int lateCount = 0;
        int earlyLeaveCount = 0;
        int absenceCount = 0;
        int completeDays = 0;

        for (AttendanceRecordDTO record : records) {
            if (record.getLateMinutes() != null && record.getLateMinutes() > 0) lateCount++;
            if (record.getEarlyLeaveMinutes() != null && record.getEarlyLeaveMinutes() > 0) earlyLeaveCount++;
            if (record.getAbsenceType() != null && (record.getAbsenceType() == 1 || record.getAbsenceType() == 2)) absenceCount++;
            if (record.getCheckInTime() != null && record.getCheckOutTime() != null) completeDays++;
        }

        double attendanceRate = totalDays > 0 ? (double) completeDays / totalDays : 0.0;

        double score = 100;
        score -= lateCount * 2;
        score -= earlyLeaveCount * 2;
        score -= absenceCount * 10;
        if (attendanceRate < 0.9) score -= 5;
        if (lateCount == 0 && earlyLeaveCount == 0 && absenceCount == 0) score += 5;

        String ratingLevel;
        if (score >= 90) ratingLevel = "A";
        else if (score >= 80) ratingLevel = "B";
        else if (score >= 70) ratingLevel = "C";
        else ratingLevel = "D";

        PerformanceRatingDTO dto = new PerformanceRatingDTO();
        dto.setRatingLevel(ratingLevel);
        dto.setRatingScore(score);
        dto.setEvaluationDate(LocalDate.now().toString());
        dto.setEvaluator(evaluator);
        dto.setComments("自动根据考勤生成");

        return dto;
    }
}
