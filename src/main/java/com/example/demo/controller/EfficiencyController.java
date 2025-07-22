package com.example.demo.controller;

import com.example.demo.dto.EmployeeEfficiencyDTO;
import com.example.demo.dto.DepartmentOverviewDTO;
import com.example.demo.service.EfficiencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/efficiency")
public class EfficiencyController {

    @Autowired
    private EfficiencyService efficiencyService;
    /**
     * 员工效率分析接口
     * @param empCode 员工工号
     * @param yearMonth 年月（格式：yyyy-MM）
     * @param standardWorkHoursPerDay 标准每日工时
     * @param overtimeRate 加班费率
     * @return 员工效率分析结果
     */
    @GetMapping("/employee")
    public EmployeeEfficiencyDTO analyzeEmployeeEfficiency(
            @RequestParam String empCode,
            @RequestParam String yearMonth,
            @RequestParam double standardWorkHoursPerDay,
            @RequestParam double overtimeRate) {
        return efficiencyService.analyzeEmployeeEfficiency(empCode, yearMonth, standardWorkHoursPerDay, overtimeRate);
    }

    /**
     * 部门效率对比接口
     * @param yearMonth 年月（格式：yyyy-MM）
     * @param standardWorkHoursPerDay 标准每日工时
     * @return 部门效率对比列表
     */
    @GetMapping("/department")
    public List<DepartmentOverviewDTO> getDepartmentEfficiencyComparison(
            @RequestParam String yearMonth,
            @RequestParam double standardWorkHoursPerDay) {
        return efficiencyService.generateDepartmentEfficiencyComparison(yearMonth, standardWorkHoursPerDay);
    }
}