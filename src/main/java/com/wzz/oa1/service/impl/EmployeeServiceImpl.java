package com.wzz.oa1.service.impl;

import com.wzz.oa1.dao.EmployeeMapper;
import com.wzz.oa1.global.Constant;
import com.wzz.oa1.global.ServerResponse;
import com.wzz.oa1.pojo.Employee;
import com.wzz.oa1.service.EmployeeService;
import com.wzz.oa1.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.provider.MD5;

import java.util.List;

/**
 * @Author: wzzap
 * @Description:
 * @Date: 2019/6/9
 **/
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public int updateEmployee(Employee employee) {
        return employeeMapper.updateByPrimaryKey(employee);
    }

    @Override
    public ServerResponse<List<Employee>> selectAll() {
        List<Employee> employeeList = employeeMapper.selectAll();
        if(employeeList.size() > 0){
            return ServerResponse.createBySuccess(employeeList);
        }
        return ServerResponse.createByErrorMessage("员工列表可能为空，查询失败！");
    }

    @Override
    public ServerResponse<String> addEmployee(Employee employee) {
        if(employee == null)
            return ServerResponse.createByErrorMessage("请填写要添加的员工信息！");
        if(employeeMapper.selectByPrimaryKey(employee.getSn()) != null)
            return ServerResponse.createByErrorMessage("添加员工失败,此员工编号已存在！");
        employee.setPassword(MD5Util.MD5EncodeUtf8(Constant.INIT_PASSWORD));
        int resultCount = employeeMapper.insertSelective(employee);
        if(resultCount > 0)
            return ServerResponse.createBySuccessMessage("添加员工成功！");
        return ServerResponse.createByErrorMessage("添加员工异常！");
    }
}
