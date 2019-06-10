package com.wzz.oa1.dao;

import com.wzz.oa1.pojo.ClaimVoucher;

public interface ClaimVoucherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClaimVoucher record);

    int insertSelective(ClaimVoucher record);

    ClaimVoucher selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClaimVoucher record);

    int updateByPrimaryKey(ClaimVoucher record);
}