package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@TableName("salary_records")
public class SalaryRecords {
    @TableId(type = IdType.AUTO)
    private Integer id; // 工资记录ID主键

    private Integer empId; // 员工ID
    private String salaryMonth; // 工资月份，格式：YYYY-MM
    private BigDecimal baseSalary; // 基本工资
    private BigDecimal overtimePay; // 加班费
    private BigDecimal bonus; // 奖金
    private BigDecimal allowance; // 津贴补助
    private BigDecimal deduction; // 扣款金额
    private BigDecimal socialInsurance; // 社会保险费
    private BigDecimal housingFund; // 住房公积金
    private BigDecimal tax; // 个人所得税
    private BigDecimal netSalary; // 实发工资
    private Integer payStatus; // 发放状态：0-未发放，1-已发放
    private LocalDate payDate; // 实际发放日期
    private String remarks; // 备注信息

    @TableField(fill = FieldFill.INSERT)
    private OffsetDateTime createdAt; // 创建时间

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private OffsetDateTime updatedAt; // 更新时间
}
