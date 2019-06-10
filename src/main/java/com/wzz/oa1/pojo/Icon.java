package com.wzz.oa1.pojo;

import java.util.Date;

public class Icon {
    private Integer id;

    private String path;

    private String eSn;

    private Date createTime;

    private Date updateTime;

    public Icon(Integer id, String path, String eSn, Date createTime, Date updateTime) {
        this.id = id;
        this.path = path;
        this.eSn = eSn;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Icon() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String geteSn() {
        return eSn;
    }

    public void seteSn(String eSn) {
        this.eSn = eSn == null ? null : eSn.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}