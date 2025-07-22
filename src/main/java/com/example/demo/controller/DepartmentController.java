package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import com.example.demo.dto.DepartmentOverviewDTO;
import com.example.demo.dto.EmployeeBasicInfoDTO;
import com.example.demo.service.DepartmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@Tag(name = "部门管理", description = "部门信息查询和统计接口")
@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // 场景四：聚合统计能力 - 统计各部门的员工数量和平均工资
    @Operation(summary = "部门员工统计分析", 
               description = "统计各部门的员工数量和平均工资，包括在职员工数、平均工资、最高工资、最低工资等聚合数据")
    @GetMapping("/stats")
    public List<Map<String, Object>> getDepartmentStats(
            @Parameter(description = "统计年月（格式：yyyy-MM）", required = true, example = "2024-01") 
            @RequestParam String yearMonth) {
        return departmentService.queryDepartmentStats(yearMonth)
            .stream().map(dto -> {
                Map<String, Object> map = new HashMap<>();
                map.put("deptName", dto.getDeptName());
                map.put("deptId", dto.getDeptId());
                map.put("employeeCount", dto.getEmployeeCount());
                map.put("avgSalary", dto.getAvgSalary());
                map.put("maxSalary", dto.getMaxSalary());
                map.put("minSalary", dto.getMinSalary());
                return map;
            }).collect(Collectors.toList());
    }

    // 部门层级统计
    @GetMapping("/hierarchy-stats")
    public List<DepartmentOverviewDTO> getDepartmentHierarchyStats() {
        return departmentService.queryDepartmentHierarchyStats();
    }

    // 部门考勤异常统计
    @GetMapping("/anomaly-stats")
    public List<DepartmentOverviewDTO> getDepartmentAnomalyStats(
            @RequestParam String yearMonth,
            @RequestParam int threshold) {
        return departmentService.queryDepartmentAnomalyStats(yearMonth, threshold);
    }

    // 场景一：查询指定部门的所有在职员工基本信息
    @Operation(summary = "查询指定部门的所有在职员工基本信息", 
               description = "根据部门ID查询该部门下所有在职员工的基本信息，包括姓名、工号、职位、入职时间等")
    @GetMapping("/employees")
    public List<EmployeeBasicInfoDTO> getDepartmentEmployees(
            @Parameter(description = "部门ID", required = true) @RequestParam int deptId) {
        return departmentService.queryDepartmentEmployees(deptId);
    }
}
