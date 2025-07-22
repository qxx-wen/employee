package com.example.demo.dto;

public class AttendanceStatisticsDTO {
    private int totalDays;
    private int completeDays;
    private int lateDays;
    private int earlyLeaveDays;
    private int absenceDays;
    private Double totalWorkHours;
    private Double totalOvertimeHours;
    private int totalLateMinutes;
    private int totalEarlyLeaveMinutes;
    private double attendanceRate;
    private Double averageWorkHours; // 平均工作时长
    private Double lateRate;         // 迟到率
    private int leaveDays;           // 请假天数
    // getter/setter

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public int getCompleteDays() {
        return completeDays;
    }

    public void setCompleteDays(int completeDays) {
        this.completeDays = completeDays;
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

    public int getAbsenceDays() {
        return absenceDays;
    }

    public void setAbsenceDays(int absenceDays) {
        this.absenceDays = absenceDays;
    }

    public Double getTotalWorkHours() {
        return totalWorkHours;
    }

    public void setTotalWorkHours(Double totalWorkHours) {
        this.totalWorkHours = totalWorkHours;
    }

    public Double getTotalOvertimeHours() {
        return totalOvertimeHours;
    }

    public void setTotalOvertimeHours(Double totalOvertimeHours) {
        this.totalOvertimeHours = totalOvertimeHours;
    }

    public int getTotalLateMinutes() {
        return totalLateMinutes;
    }

    public void setTotalLateMinutes(int totalLateMinutes) {
        this.totalLateMinutes = totalLateMinutes;
    }

    public int getTotalEarlyLeaveMinutes() {
        return totalEarlyLeaveMinutes;
    }

    public void setTotalEarlyLeaveMinutes(int totalEarlyLeaveMinutes) {
        this.totalEarlyLeaveMinutes = totalEarlyLeaveMinutes;
    }

    public double getAttendanceRate() {
        return attendanceRate;
    }

    public void setAttendanceRate(double attendanceRate) {
        this.attendanceRate = attendanceRate;
    }

    public Double getAverageWorkHours() { return averageWorkHours; }
    public void setAverageWorkHours(Double averageWorkHours) { this.averageWorkHours = averageWorkHours; }
    public Double getLateRate() { return lateRate; }
    public void setLateRate(Double lateRate) { this.lateRate = lateRate; }
    public int getLeaveDays() { return leaveDays; }
    public void setLeaveDays(int leaveDays) { this.leaveDays = leaveDays; }
}
