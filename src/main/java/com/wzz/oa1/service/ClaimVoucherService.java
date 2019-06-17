package com.wzz.oa1.service;

import com.wzz.oa1.pojo.ClaimVoucher;
import com.wzz.oa1.pojo.ClaimVoucherItem;
import com.wzz.oa1.pojo.DealRecord;

import java.util.List;

/**
 * @Author: wzzap
 * @Description:
 * @Date: 2019/6/16
 **/
public interface ClaimVoucherService {

    void save(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items);

    ClaimVoucher select(int id);

    List<ClaimVoucherItem> getItems(int cvId);

    List<DealRecord> getRecords(int cvId);

    /**
     * 获取自己的报销单
     * @param sn 员工编号
     * @return
     */
    List<ClaimVoucher> getForSelf(String sn);

    /**
     * 获取待处理的报销单
     * @param sn 员工编号
     * @return
     */
    List<ClaimVoucher> getForDeal(String sn);

    void update(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items);

    void submit(int id);

    /**
     * 审核报销单
     * @param dealRecord
     */
    void deal(DealRecord dealRecord);

}
