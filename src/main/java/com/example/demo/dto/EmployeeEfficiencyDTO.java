package com.example.demo.dto;

public class EmployeeEfficiencyDTO {
    private String empCode;
    private String empName;
    private String deptName;
    private Double baseSalary;
    private int totalWorkDays;
    private Double standardWorkHours;
    private Double actualWorkHours;
    private Double overtimeHours;
    private Double efficiencyRate;
    private Double overtimeRate;
    private Double overtimePay;
    private Double totalPay;
    private int lateDays;
    private int earlyLeaveDays;
    private int absentDays;
    private Double averageWorkHoursPerDay;

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
