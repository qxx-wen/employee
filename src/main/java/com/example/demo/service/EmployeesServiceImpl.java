package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Employees;
import com.example.demo.mapper.EmployeesMapper;
import org.springframework.stereotype.Service;

@Service
public class EmployeesServiceImpl extends ServiceImpl<EmployeesMapper, Employees> implements EmployeesService {
    // 可扩展自定义方法
}
