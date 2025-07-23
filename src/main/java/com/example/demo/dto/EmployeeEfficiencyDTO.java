package com.example.demo.dto;

public class EmployeeEfficiencyDTO {
    private String empCode;// 员工工号
    private String empName;// 员工姓名
    private String deptName;// 部门名称
    private Double baseSalary;// 基本薪资
    private int totalWorkDays;// 总工作天数
    private Double standardWorkHours;// 标准工作时长
    private Double actualWorkHours;// 实际工作时长
    private Double overtimeHours;// 加班时长
    private Double efficiencyRate;// 效率
    private Double overtimeRate;// 加班率
    private Double overtimePay;// 加班费
    private Double totalPay;// 总薪资
    private int lateDays;// 迟到天数
    private int earlyLeaveDays;// 早退天数
    private int absentDays;// 缺勤天数
    private Double averageWorkHoursPerDay;// 平均工作时长

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public int getTotalWorkDays() {
        return totalWorkDays;
    }

    public void setTotalWorkDays(int totalWorkDays) {
        this.totalWorkDays = totalWorkDays;
    }

    public Double getStandardWorkHours() {
        return standardWorkHours;
    }

    public void setStandardWorkHours(Double standardWorkHours) {
        this.standardWorkHours = standardWorkHours;
    }

    public Double getActualWorkHours() {
        return actualWorkHours;
    }

    public void setActualWorkHours(Double actualWorkHours) {
        this.actualWorkHours = actualWorkHours;
    }

    public Double getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(Double overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public Double getEfficiencyRate() {
        return efficiencyRate;
    }

    public void setEfficiencyRate(Double efficiencyRate) {
        this.efficiencyRate = efficiencyRate;
    }

    public Double getOvertimeRate() {
        return overtimeRate;
    }

    public void setOvertimeRate(Double overtimeRate) {
        this.overtimeRate = overtimeRate;
    }

    public Double getOvertimePay() {
        return overtimePay;
    }

    public void setOvertimePay(Double overtimePay) {
        this.overtimePay = overtimePay;
    }

    public Double getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(Double totalPay) {
        this.totalPay = totalPay;
    }

    public int getLateDays() {
        return lateDays;
    }

    public void setLateDays(int lateDays) {
        this.lateDays = lateDays;
    }

    public int getEarlyLeaveDays() {
        return earlyLeaveDays;
    }

    public void setEarlyLeaveDays(int earlyLeaveDays) {
        this.earlyLeaveDays = earlyLeaveDays;
    }

    public int getAbsentDays() {
        return absentDays;
    }

    public void setAbsentDays(int absentDays) {
        this.absentDays = absentDays;
    }

    public Double getAverageWorkHoursPerDay() {
        return averageWorkHoursPerDay;
    }

    public void setAverageWorkHoursPerDay(Double averageWorkHoursPerDay) {
        this.averageWorkHoursPerDay = averageWorkHoursPerDay;
    }
}
