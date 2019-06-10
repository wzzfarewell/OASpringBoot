package com.wzz.oa1.dao;

import com.wzz.oa1.pojo.DealRecord;

public interface DealRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DealRecord record);

    int insertSelective(DealRecord record);

    DealRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DealRecord record);

    int updateByPrimaryKey(DealRecord record);
}