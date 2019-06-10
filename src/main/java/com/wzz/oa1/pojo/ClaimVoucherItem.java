package com.wzz.oa1.pojo;

public class ClaimVoucherItem {
    private Integer id;

    private Integer claimVoucherId;

    private String item;

    private Double amount;

    private String comment;

    public ClaimVoucherItem(Integer id, Integer claimVoucherId, String item, Double amount, String comment) {
        this.id = id;
        this.claimVoucherId = claimVoucherId;
        this.item = item;
        this.amount = amount;
        this.comment = comment;
    }

    public ClaimVoucherItem() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClaimVoucherId() {
        return claimVoucherId;
    }

    public void setClaimVoucherId(Integer claimVoucherId) {
        this.claimVoucherId = claimVoucherId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item == null ? null : item.trim();
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}