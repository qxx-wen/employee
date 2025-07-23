package com.example.demo.dto;

import java.util.List;
import com.example.demo.dto.AttendanceStatisticsDTO;

public class DepartmentAttendanceReportDTO {
    private String deptName;// 部门名称
    private int deptId;// 部门ID
    private int totalEmployees;// 员工总数
    private AttendanceStatisticsDTO attendanceStats;// 考勤统计
    private List<EmployeeAttendanceDetailDTO> employeeAttendanceList;// 员工考勤明细

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public int getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(int totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public AttendanceStatisticsDTO getAttendanceStats() {
        return attendanceStats;
    }

    public void setAttendanceStats(AttendanceStatisticsDTO attendanceStats) {
        this.attendanceStats = attendanceStats;
    }

    public List<EmployeeAttendanceDetailDTO> getEmployeeAttendanceList() {
        return employeeAttendanceList;
    }
    public void setEmployeeAttendanceList(List<EmployeeAttendanceDetailDTO> employeeAttendanceList) {
        this.employeeAttendanceList = employeeAttendanceList;
    }

// getter/setter
}
