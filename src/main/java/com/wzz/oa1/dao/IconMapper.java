package com.wzz.oa1.dao;

import com.wzz.oa1.pojo.Icon;

public interface IconMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Icon record);

    int insertSelective(Icon record);

    Icon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Icon record);

    int updateByPrimaryKey(Icon record);

    Icon selectByEmployeeSn(String esn);
}