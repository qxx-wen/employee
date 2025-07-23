package com.example.demo.dto;

public class EmployeeComprehensiveDTO {
    private EmployeeBasicInfoDTO basicInfo;// 员工基本信息
    private AttendanceStatisticsDTO attendanceStats;// 考勤统计
    private SalaryDetailDTO salaryDetail;// 薪资明细
    private PerformanceRatingDTO performanceRating;// 绩效评分

    public EmployeeBasicInfoDTO getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(EmployeeBasicInfoDTO basicInfo) {
        this.basicInfo = basicInfo;
    }

    public AttendanceStatisticsDTO getAttendanceStats() {
        return attendanceStats;
    }

    public void setAttendanceStats(AttendanceStatisticsDTO attendanceStats) {
        this.attendanceStats = attendanceStats;
    }

    public SalaryDetailDTO getSalaryDetail() {
        return salaryDetail;
    }

    public void setSalaryDetail(SalaryDetailDTO salaryDetail) {
        this.salaryDetail = salaryDetail;
    }

    public PerformanceRatingDTO getPerformanceRating() {
        return performanceRating;
    }

    public void setPerformanceRating(PerformanceRatingDTO performanceRating) {
        this.performanceRating = performanceRating;
    }

// getter/setter
}
