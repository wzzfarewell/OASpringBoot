package com.wzz.oa1.service.impl;

import com.wzz.oa1.dao.DepartmentMapper;
import com.wzz.oa1.pojo.Department;
import com.wzz.oa1.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wzzap
 * @Description:
 * @Date: 2019/6/9
 **/
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> selectAll() {
        return departmentMapper.selectAll();
    }
}
