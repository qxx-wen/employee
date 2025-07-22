package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.EmploymentRecords;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmploymentRecordsMapper extends BaseMapper<EmploymentRecords> {
    @Select("SELECT 1")
    int testSelect();
} 
