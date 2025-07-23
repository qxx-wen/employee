package com.example.demo.dto;

public class AttendanceRecordDTO {
    private String empCode;// 员工工号
    private String empName;// 员工姓名
    private String checkInTime;// 上班时间
    private String checkOutTime;// 下班时间
    private String status;// 状态
    private String attendanceDate;// 考勤日期
    private Double workHours;// 工作时长
    private Integer lateMinutes;// 迟到分钟数
    private Integer earlyLeaveMinutes;// 早退分钟数
    private Integer absenceType;// 缺勤类型

    // 构造函数
    public AttendanceRecordDTO() {}

    public AttendanceRecordDTO(String empCode, String empName, String checkInTime, String checkOutTime, String status) {
        this.empCode = empCode;
        this.empName = empName;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.status = status;
    }

    // Getter和Setter方法
    public String getEmpCode() { return empCode; }
    public void setEmpCode(String empCode) { this.empCode = empCode; }

    public String getEmpName() { return empName; }
    public void setEmpName(String empName) { this.empName = empName; }

    public String getCheckInTime() { return checkInTime; }
    public void setCheckInTime(String checkInTime) { this.checkInTime = checkInTime; }

    public String getCheckOutTime() { return checkOutTime; }
    public void setCheckOutTime(String checkOutTime) { this.checkOutTime = checkOutTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getAttendanceDate() { return attendanceDate; }
    public void setAttendanceDate(String attendanceDate) { this.attendanceDate = attendanceDate; }

    public Double getWorkHours() { return workHours; }
    public void setWorkHours(Double workHours) { this.workHours = workHours; }

    public Integer getLateMinutes() { return lateMinutes; }
    public void setLateMinutes(Integer lateMinutes) { this.lateMinutes = lateMinutes; }

    public Integer getEarlyLeaveMinutes() { return earlyLeaveMinutes; }
    public void setEarlyLeaveMinutes(Integer earlyLeaveMinutes) { this.earlyLeaveMinutes = earlyLeaveMinutes; }

    public Integer getAbsenceType() { return absenceType; }
    public void setAbsenceType(Integer absenceType) { this.absenceType = absenceType; }
}
