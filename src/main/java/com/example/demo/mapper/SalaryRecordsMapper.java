package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.SalaryRecords;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SalaryRecordsMapper extends BaseMapper<SalaryRecords> {
    @Select("SELECT 1")
    int testSelect();
} 
