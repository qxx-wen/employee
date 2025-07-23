package com.example.demo.dto;

public class SalaryDetailDTO {
    private String salaryMonth;// 薪资月份
    private Double baseSalary;// 基本薪资
    private Double overtimePay;// 加班费
    private Double bonus;// 奖金
    private Double allowance;// 津贴
    private Double deduction;// 扣除
    private Double socialInsurance;// 社保
    private Double housingFund;// 公积金
    private Double tax;// 税
    private Double netSalary;// 净薪资
    private String payStatus;// 支付状态
    private String payDate;// 支付日期
    private String remarks;// 备注

    public String getSalaryMonth() {
        return salaryMonth;
    }

    public void setSalaryMonth(String salaryMonth) {
        this.salaryMonth = salaryMonth;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Double getOvertimePay() {
        return overtimePay;
    }

    public void setOvertimePay(Double overtimePay) {
        this.overtimePay = overtimePay;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public Double getAllowance() {
        return allowance;
    }

    public void setAllowance(Double allowance) {
        this.allowance = allowance;
    }

    public Double getDeduction() {
        return deduction;
    }

    public void setDeduction(Double deduction) {
        this.deduction = deduction;
    }

    public Double getSocialInsurance() {
        return socialInsurance;
    }

    public void setSocialInsurance(Double socialInsurance) {
        this.socialInsurance = socialInsurance;
    }

    public Double getHousingFund() {
        return housingFund;
    }

    public void setHousingFund(Double housingFund) {
        this.housingFund = housingFund;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(Double netSalary) {
        this.netSalary = netSalary;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
// getter/setter
}
