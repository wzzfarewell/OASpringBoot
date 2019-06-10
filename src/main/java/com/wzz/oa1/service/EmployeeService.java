package com.wzz.oa1.service;

import com.wzz.oa1.global.ServerResponse;
import com.wzz.oa1.pojo.Employee;

import java.util.List;

/**
 * @Author: wzzap
 * @Description:
 * @Date: 2019/6/9
 **/
public interface EmployeeService {

    int updateEmployee(Employee employee);

    ServerResponse<List<Employee>> selectAll();

    ServerResponse<String> addEmployee(Employee employee);
}
