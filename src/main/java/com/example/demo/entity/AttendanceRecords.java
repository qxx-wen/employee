package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@TableName("attendance_records")
public class AttendanceRecords {
    @TableId(type = IdType.AUTO)
    private Integer id; // 考勤记录ID主键

    private Integer empId; // 员工ID
    private LocalDate attendanceDate; // 考勤日期
    private OffsetDateTime clockInTime; // 上班打卡时间
    private OffsetDateTime clockOutTime; // 下班打卡时间
    private BigDecimal workHours; // 实际工作时长（小时）
    private BigDecimal overtimeHours; // 加班时长（小时）
    private Integer lateMinutes; // 迟到分钟数
    private Integer earlyLeaveMinutes; // 早退分钟数
    private Integer absenceType; // 缺勤类型：0-正常，1-请假，2-旷工，3-出差
    private String remarks; // 备注信息

    @TableField(fill = FieldFill.INSERT)
    private OffsetDateTime createdAt; // 创建时间

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private OffsetDateTime updatedAt; // 更新时间
}
