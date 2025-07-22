package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@TableName("employment_records")
public class EmploymentRecords {
    @TableId(type = IdType.AUTO)
    private Integer id; // 人事记录ID主键

    private Integer empId; // 员工ID
    private Integer recordType; // 记录类型：1-入职，2-离职，3-调岗，4-升职，5-降职
    private LocalDate effectiveDate; // 生效日期
    private String reason; // 变动原因
    private Integer deptId; // 变动后部门ID
    private String position; // 变动后职位
    private BigDecimal salary; // 变动后薪资
    private Integer operatorId; // 操作人员工ID
    private String remarks; // 备注信息

    @TableField(fill = FieldFill.INSERT)
    private OffsetDateTime createdAt; // 创建时间
} 