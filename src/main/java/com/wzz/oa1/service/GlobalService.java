package com.wzz.oa1.service;

import com.wzz.oa1.pojo.Employee;

/**
 * @Author: wzzap
 * @Description:
 * @Date: 2019/6/8
 **/
public interface GlobalService {

    Employee login(String sn, String password);

    Boolean checkPassword(String sn, String oldPassword);
}
