package com.wzz.oa1.pojo;

public class Department {
    private String sn;

    private String name;

    private String address;

    public Department(String sn, String name, String address) {
        this.sn = sn;
        this.name = name;
        this.address = address;
    }

    public Department() {
        super();
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}