package com.wzz.oa1.service;

import com.wzz.oa1.global.ServerResponse;
import com.wzz.oa1.pojo.Department;

import java.util.List;

/**
 * @Author: wzzap
 * @Description:
 * @Date: 2019/6/9
 **/
public interface DepartmentService {

    List<Department> selectAll();
}
