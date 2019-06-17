package com.wzz.oa1.dto;

import com.wzz.oa1.pojo.ClaimVoucher;
import com.wzz.oa1.pojo.ClaimVoucherItem;

import java.util.List;

/**
 * @Author: wzzap
 * @Description:
 * @Date: 2019/6/16
 **/
public class ClaimVoucherInfo {
    private ClaimVoucher claimVoucher;
    private List<ClaimVoucherItem> items;

    public ClaimVoucher getClaimVoucher() {
        return claimVoucher;
    }

    public void setClaimVoucher(ClaimVoucher claimVoucher) {
        this.claimVoucher = claimVoucher;
    }

    public List<ClaimVoucherItem> getItems() {
        return items;
    }

    public void setItems(List<ClaimVoucherItem> items) {
        this.items = items;
    }
}
