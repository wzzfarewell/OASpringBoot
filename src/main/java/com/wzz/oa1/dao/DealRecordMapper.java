package com.wzz.oa1.dao;

import com.wzz.oa1.pojo.DealRecord;

import java.util.List;

public interface DealRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DealRecord record);

    int insertSelective(DealRecord record);

    DealRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DealRecord record);

    int updateByPrimaryKey(DealRecord record);

    List<DealRecord> selectByCvId(Integer cvId);

}