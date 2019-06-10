package com.wzz.oa1.dao;

import com.wzz.oa1.pojo.Department;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(String sn);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(String sn);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<Department> selectAll();
}