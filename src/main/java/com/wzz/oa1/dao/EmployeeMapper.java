package com.wzz.oa1.dao;

import com.wzz.oa1.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(String sn);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(String sn);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    int checkPassword(@Param("sn") String sn, @Param("oldPassword") String oldPassword);

    List<Employee> selectAll();
}