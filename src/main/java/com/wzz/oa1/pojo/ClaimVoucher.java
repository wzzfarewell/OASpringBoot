package com.wzz.oa1.pojo;

import java.util.Date;

public class ClaimVoucher {
    private Integer id;

    private String cause;

    private String createSn;

    private Date createTime;

    private String nextDealSn;

    private Double totalAmount;

    private String status;

    public ClaimVoucher(Integer id, String cause, String createSn, Date createTime, String nextDealSn, Double totalAmount, String status) {
        this.id = id;
        this.cause = cause;
        this.createSn = createSn;
        this.createTime = createTime;
        this.nextDealSn = nextDealSn;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public ClaimVoucher() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause == null ? null : cause.trim();
    }

    public String getCreateSn() {
        return createSn;
    }

    public void setCreateSn(String createSn) {
        this.createSn = createSn == null ? null : createSn.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNextDealSn() {
        return nextDealSn;
    }

    public void setNextDealSn(String nextDealSn) {
        this.nextDealSn = nextDealSn == null ? null : nextDealSn.trim();
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}