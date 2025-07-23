package com.example.demo.dto;

import java.util.List;

public class AttendanceAnomalyDTO {
    private String empCode;// 员工工号
    private String empName;// 员工姓名
    private String anomalyType;// 异常类型
    private int anomalyCount;// 异常次数
    private String yearMonth;// 年月

    // 构造函数
    public AttendanceAnomalyDTO() {}

    public AttendanceAnomalyDTO(String empCode, String empName, String anomalyType, 
                               int anomalyCount, String yearMonth) {
        this.empCode = empCode;
        this.empName = empName;
        this.anomalyType = anomalyType;
        this.anomalyCount = anomalyCount;
        this.yearMonth = yearMonth;
    }

    // Getter和Setter方法
    public String getEmpCode() { return empCode; }
    public void setEmpCode(String empCode) { this.empCode = empCode; }

    public String getEmpName() { return empName; }
    public void setEmpName(String empName) { this.empName = empName; }

    public String getAnomalyType() { return anomalyType; }
    public void setAnomalyType(String anomalyType) { this.anomalyType = anomalyType; }

    public int getAnomalyCount() { return anomalyCount; }
    public void setAnomalyCount(int anomalyCount) { this.anomalyCount = anomalyCount; }

    public String getYearMonth() { return yearMonth; }
    public void setYearMonth(String yearMonth) { this.yearMonth = yearMonth; }
}
