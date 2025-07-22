package com.example.demo.dto;

import java.util.List;
import com.example.demo.dto.AttendanceStatisticsDTO;

public class DepartmentAttendanceReportDTO {
    private String deptName;
    private int deptId;
    private int totalEmployees;
    private AttendanceStatisticsDTO attendanceStats;
    private List<EmployeeAttendanceDetailDTO> employeeAttendanceList;

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
