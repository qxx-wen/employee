package com.example.demo.dto;

public class EmployeeAttendanceDetailDTO {
    private String empCode;
    private String empName;
    private String attendanceDate;
    private String checkInTime;
    private String checkOutTime;
    private Integer lateMinutes;
    private Integer earlyLeaveMinutes;
    private Integer absenceType;
    private Double workHours;
    private String remarks;

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