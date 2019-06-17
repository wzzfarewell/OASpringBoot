package com.wzz.oa1.dao;

import com.wzz.oa1.pojo.ClaimVoucherItem;

import java.util.List;

public interface ClaimVoucherItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClaimVoucherItem record);

    int insertSelective(ClaimVoucherItem record);

    ClaimVoucherItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClaimVoucherItem record);

    int updateByPrimaryKey(ClaimVoucherItem record);

    List<ClaimVoucherItem> selectItems(Integer cvId);

}