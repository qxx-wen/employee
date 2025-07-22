package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@TableName("employees")
public class Employees {
    @TableId(type = IdType.AUTO)
    private Integer id; // 员工ID主键

    private String empCode; // 员工工号，唯一标识
    private String empName; // 员工姓名
    private String gender; // 性别：M-男，F-女
    private LocalDate birthDate; // 出生日期
    private String idCard; // 身份证号码
    private String phone; // 联系电话
    private String email; // 电子邮箱
    private String address; // 家庭住址
    private Integer deptId; // 所属部门ID
    private String position; // 职位名称
    private String level; // 职级等级
    private LocalDate hireDate; // 入职日期
    private Integer contractType; // 合同类型：1-正式员工，2-合同工，3-实习生
    private Integer employmentStatus; // 在职状态：1-在职，0-离职
    private BigDecimal baseSalary; // 基本工资

    @TableField(fill = FieldFill.INSERT)
    private OffsetDateTime createdAt; // 创建时间

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private OffsetDateTime updatedAt; // 更新时间
}
