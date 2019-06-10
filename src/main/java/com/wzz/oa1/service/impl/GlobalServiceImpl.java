package com.wzz.oa1.service.impl;

import com.wzz.oa1.dao.EmployeeMapper;
import com.wzz.oa1.dao.IconMapper;
import com.wzz.oa1.global.ServerResponse;
import com.wzz.oa1.pojo.Employee;
import com.wzz.oa1.pojo.Icon;
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
    @Autowired
    private IconMapper iconMapper;

    @Override
    public Employee login(String sn, String password) {
        Employee employee = employeeMapper.selectByPrimaryKey(sn);
        if(employee != null && MD5Util.MD5EncodeUtf8(password).equals(employee.getPassword())){
            employee.setPassword(StringUtils.EMPTY);
            Icon icon = iconMapper.selectByEmployeeSn(sn);
            if(icon != null)
                employee.setIcon(icon);
            return employee;
        }
        return null;
    }

    @Override
    public Boolean checkPassword(String sn, String oldPassword) {
        int resultCount = employeeMapper.checkPassword(sn, MD5Util.MD5EncodeUtf8(oldPassword));
        return resultCount > 0;
    }

    @Override
    public ServerResponse<Employee> uploadIcon(String path, Employee employee) {
        Icon icon1 = iconMapper.selectByEmployeeSn(employee.getSn());
        int resultCount;
        if(icon1 != null){
            icon1.setPath(path);
            resultCount = iconMapper.updateByPrimaryKey(icon1);
        }else{
            Icon icon = new Icon();
            icon.seteSn(employee.getSn());
            icon.setPath(path);
            resultCount = iconMapper.insert(icon);
        }
        if(resultCount < 1)
            return ServerResponse.createByErrorMessage("上传头像失败！");
        employee.setIcon(iconMapper.selectByEmployeeSn(employee.getSn()));
        return ServerResponse.createBySuccess(employee);
    }
}
