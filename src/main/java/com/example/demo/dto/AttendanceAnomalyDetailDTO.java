package com.example.demo.dto;

import java.time.LocalDate;

public class AttendanceAnomalyDetailDTO {
    private String empCode;// 员工工号
    private String empName;// 员工姓名
    private LocalDate date;// 日期
    private String checkInTime;// 上班时间
    private String checkOutTime;// 下班时间
    private String anomalyType;// 异常类型
    private String description;// 异常描述

    // 构造函数
    public AttendanceAnomalyDetailDTO() {}

    public AttendanceAnomalyDetailDTO(String empCode, String empName, LocalDate date,
                                     String checkInTime, String checkOutTime,
                                     String anomalyType, String description) {
        this.empCode = empCode;
        this.empName = empName;
        this.date = date;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.anomalyType = anomalyType;
        this.description = description;
    }

    // Getter和Setter方法
    public String getEmpCode() { return empCode; }
    public void setEmpCode(String empCode) { this.empCode = empCode; }

    public String getEmpName() { return empName; }
    public void setEmpName(String empName) { this.empName = empName; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getCheckInTime() { return checkInTime; }
    public void setCheckInTime(String checkInTime) { this.checkInTime = checkInTime; }

    public String getCheckOutTime() { return checkOutTime; }
    public void setCheckOutTime(String checkOutTime) { this.checkOutTime = checkOutTime; }

    public String getAnomalyType() { return anomalyType; }
    public void setAnomalyType(String anomalyType) { this.anomalyType = anomalyType; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
