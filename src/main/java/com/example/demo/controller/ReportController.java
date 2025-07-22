package com.example.demo.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.demo.dto.DepartmentAttendanceReportDTO;
import com.example.demo.dto.DepartmentOverviewDTO;
import com.example.demo.service.ReportService;

@Tag(name = "报表接口")
@RestController
@RequestMapping("/api/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // 场景七：动态统计分析 - 部门月度考勤统计报表
    @Operation(summary = "部门月度考勤统计报表", 
               description = "生成部门月度考勤统计报表，包含出勤率、平均工作时长、迟到率、请假统计等复杂计算和百分比统计")
    @GetMapping("/department-attendance")
    public DepartmentAttendanceReportDTO getDepartmentAttendanceReport(
            @Parameter(description = "部门ID", required = true, example = "1") 
            @RequestParam int deptId,
            @Parameter(description = "统计月份（格式：yyyy-MM）", required = true, example = "2024-01") 
            @RequestParam String yearMonth) {
        return reportService.generateDepartmentAttendanceReport(deptId, yearMonth);
    }

    @Operation(summary = "部门效率对比")
    @GetMapping("/department-efficiency")
    public List<DepartmentOverviewDTO> getDepartmentEfficiencyComparison(
            @RequestParam String yearMonth) {
        return reportService.generateDepartmentEfficiencyComparison(yearMonth);
    }
}