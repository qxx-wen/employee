package com.example.demo.dto;

public class EmployeeAttendanceDetailDTO {
    private String empCode;// 员工工号
    private String empName;// 员工姓名
    private String attendanceDate;// 考勤日期
    private String checkInTime;// 上班时间
    private String checkOutTime;// 下班时间
    private Integer lateMinutes;// 迟到分钟数
    private Integer earlyLeaveMinutes;// 早退分钟数
    private Integer absenceType;// 缺勤类型
    private Double workHours;// 工作时长
    private String remarks;// 备注

    public String getEmpCode() { return empCode; }
    public void setEmpCode(String empCode) { this.empCode = empCode; }
    public String getEmpName() { return empName; }
    public void setEmpName(String empName) { this.empName = empName; }
    public String getAttendanceDate() { return attendanceDate; }
    public void setAttendanceDate(String attendanceDate) { this.attendanceDate = attendanceDate; }
    public String getCheckInTime() { return checkInTime; }
    public void setCheckInTime(String checkInTime) { this.checkInTime = checkInTime; }
    public String getCheckOutTime() { return checkOutTime; }
    public void setCheckOutTime(String checkOutTime) { this.checkOutTime = checkOutTime; }
    public Integer getLateMinutes() { return lateMinutes; }
    public void setLateMinutes(Integer lateMinutes) { this.lateMinutes = lateMinutes; }
    public Integer getEarlyLeaveMinutes() { return earlyLeaveMinutes; }
    public void setEarlyLeaveMinutes(Integer earlyLeaveMinutes) { this.earlyLeaveMinutes = earlyLeaveMinutes; }
    public Integer getAbsenceType() { return absenceType; }
    public void setAbsenceType(Integer absenceType) { this.absenceType = absenceType; }
    public Double getWorkHours() { return workHours; }
    public void setWorkHours(Double workHours) { this.workHours = workHours; }
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
} 