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

    @Override
    public int remove(String sn) {
        return departmentMapper.deleteByPrimaryKey(sn);
    }

    @Override
    public Department select(String sn) {
        return departmentMapper.selectByPrimaryKey(sn);
    }

    @Override
    public int update(Department department) {
        return departmentMapper.updateByPrimaryKey(department);
    }

    @Override
    public int add(Department department) {
        return departmentMapper.insert(department);
    }
}
