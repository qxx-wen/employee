package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.demo.dto.EmployeeBasicInfoDTO;
import com.example.demo.dto.EmployeeComprehensiveDTO;
import com.example.demo.dto.EmployeeHistoryDTO;
import com.example.demo.dto.EmployeeEfficiencyDTO;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.EfficiencyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@Tag(name = "员工管理", description = "员工信息查询和管理接口")
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EfficiencyService efficiencyService;

    // 查询员工基本信息
    @GetMapping("/info")
    public EmployeeBasicInfoDTO getEmployeeInfo(
            @RequestParam String empCode) {
        return employeeService.queryEmployeeBasicInfo(empCode);
    }

    // 查询员工综合信息
    @GetMapping("/comprehensive")
    public EmployeeComprehensiveDTO getEmployeeComprehensiveInfo(
            @RequestParam String empCode,
            @RequestParam String yearMonth) {
        return employeeService.queryEmployeeComprehensiveInfo(empCode, yearMonth);
    }

    // 查询员工历史记录
    @GetMapping("/history")
    public List<EmployeeHistoryDTO> getEmployeeHistory(
            @RequestParam String empCode) {
        return employeeService.queryEmployeeHistory(empCode);
    }

    // 查询员工效率分析
    @GetMapping("/efficiency")
    public EmployeeEfficiencyDTO analyzeEmployeeEfficiency(
            @RequestParam String empCode,
            @RequestParam String yearMonth,
            @RequestParam double standardWorkHoursPerDay,
            @RequestParam double overtimeRate,
            @RequestParam double hourlyRate) {
        return efficiencyService.analyzeEmployeeEfficiency(empCode, yearMonth, standardWorkHoursPerDay, overtimeRate);
    }
}
