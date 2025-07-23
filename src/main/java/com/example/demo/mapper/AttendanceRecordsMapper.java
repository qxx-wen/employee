package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.AttendanceRecords;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper 
public interface AttendanceRecordsMapper extends BaseMapper<AttendanceRecords> {
    @Select("SELECT 1")
    int testSelect();
} 