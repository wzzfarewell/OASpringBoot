package com.wzz.oa1.service.impl;

import com.wzz.oa1.dao.EmployeeMapper;
import com.wzz.oa1.pojo.Employee;
import com.wzz.oa1.service.GlobalService;
import com.wzz.oa1.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wzzap
 * @Description:
 * @Date: 2019/6/8
 **/
@Service
public class GlobalServiceImpl implements GlobalService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Employee login(String sn, String password) {
        Employee employee = employeeMapper.selectByPrimaryKey(sn);
        if(employee != null && MD5Util.MD5EncodeUtf8(password).equals(employee.getPassword())){
            employee.setPassword(StringUtils.EMPTY);
            return employee;
        }
        return null;
    }

    @Override
    public Boolean checkPassword(String sn, String oldPassword) {
        int resultCount = employeeMapper.checkPassword(sn, MD5Util.MD5EncodeUtf8(oldPassword));
        return resultCount > 0;
    }
}
