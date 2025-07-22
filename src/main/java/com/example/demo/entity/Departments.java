package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.OffsetDateTime;

@Data
@TableName("departments")
public class Departments {
    @TableId(type = IdType.AUTO)
    private Integer id; // 部门ID主键

    private String deptCode; // 部门编码，唯一标识
    private String deptName; // 部门名称
    private Integer parentId; // 上级部门ID
    private Integer managerId; // 部门负责人员工ID
    private Integer level; // 部门层级
    private Integer sortOrder; // 排序号
    private Integer status; // 部门状态：1-正常，0-停用
    private String description; // 部门描述信息

    @TableField(fill = FieldFill.INSERT)
    private OffsetDateTime createdAt; // 创建时间

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private OffsetDateTime updatedAt; // 更新时间
}
