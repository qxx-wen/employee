package com.example.demo.dto;

public class DepartmentOverviewDTO {
    // 基础信息
    private String deptName;// 部门名称
    private Integer deptId;// 部门ID
    // 层级信息
    private Integer level;// 层级
    private Integer depth;// 深度
    // 人员统计
    private Integer employeeCount; // 员工数量
    private Integer totalEmployees;// 总员工数量
    // 薪资统计
    private Double avgSalary;// 平均薪资
    private Double maxSalary;// 最高薪资
    private Double minSalary;// 最低薪资
    // 效率统计
    private Double totalWorkHours;// 总工作时长
    private Double totalOvertimeHours;// 总加班时长
    private Double avgWorkHoursPerDay;// 平均工作时长
    private Double efficiencyRate;// 效率率
    private Double overtimeRate;// 加班率
    // 异常统计
    private Integer anomalyEmployees;// 异常员工数量
    private Integer totalAnomalies;// 总异常次数
    private Double anomalyRate;// 异常率

    // getter/setter
    public String getDeptName() { return deptName; }
    public void setDeptName(String deptName) { this.deptName = deptName; }

    public Integer getDeptId() { return deptId; }
    public void setDeptId(Integer deptId) { this.deptId = deptId; }

    public Integer getLevel() { return level; }
    public void setLevel(Integer level) { this.level = level; }

    public Integer getDepth() { return depth; }
    public void setDepth(Integer depth) { this.depth = depth; }

    public Integer getEmployeeCount() { return employeeCount; }
    public void setEmployeeCount(Integer employeeCount) { this.employeeCount = employeeCount; }

    public Integer getTotalEmployees() { return totalEmployees; }
    public void setTotalEmployees(Integer totalEmployees) { this.totalEmployees = totalEmployees; }

    public Double getAvgSalary() { return avgSalary; }
    public void setAvgSalary(Double avgSalary) { this.avgSalary = avgSalary; }

    public Double getMaxSalary() { return maxSalary; }
    public void setMaxSalary(Double maxSalary) { this.maxSalary = maxSalary; }

    public Double getMinSalary() { return minSalary; }
    public void setMinSalary(Double minSalary) { this.minSalary = minSalary; }

    public Double getTotalWorkHours() { return totalWorkHours; }
    public void setTotalWorkHours(Double totalWorkHours) { this.totalWorkHours = totalWorkHours; }

    public Double getTotalOvertimeHours() { return totalOvertimeHours; }
    public void setTotalOvertimeHours(Double totalOvertimeHours) { this.totalOvertimeHours = totalOvertimeHours; }

    public Double getAvgWorkHoursPerDay() { return avgWorkHoursPerDay; }
    public void setAvgWorkHoursPerDay(Double avgWorkHoursPerDay) { this.avgWorkHoursPerDay = avgWorkHoursPerDay; }

    public Double getEfficiencyRate() { return efficiencyRate; }
    public void setEfficiencyRate(Double efficiencyRate) { this.efficiencyRate = efficiencyRate; }

    public Double getOvertimeRate() { return overtimeRate; }
    public void setOvertimeRate(Double overtimeRate) { this.overtimeRate = overtimeRate; }

    public Integer getAnomalyEmployees() { return anomalyEmployees; }
    public void setAnomalyEmployees(Integer anomalyEmployees) { this.anomalyEmployees = anomalyEmployees; }

    public Integer getTotalAnomalies() { return totalAnomalies; }
    public void setTotalAnomalies(Integer totalAnomalies) { this.totalAnomalies = totalAnomalies; }

    public Double getAnomalyRate() { return anomalyRate; }
    public void setAnomalyRate(Double anomalyRate) { this.anomalyRate = anomalyRate; }
} 