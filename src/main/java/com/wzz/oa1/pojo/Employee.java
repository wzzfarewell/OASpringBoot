package com.wzz.oa1.pojo;

public class Employee {
    private String sn;

    private String password;

    private String name;

    private String departmentSn;

    private String post;

    private Department department;

    private Icon icon;

    public Employee(String sn, String password, String name, String departmentSn, String post) {
        this.sn = sn;
        this.password = password;
        this.name = name;
        this.departmentSn = departmentSn;
        this.post = post;
    }

    public Employee() {
        super();
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDepartmentSn() {
        return departmentSn;
    }

    public void setDepartmentSn(String departmentSn) {
        this.departmentSn = departmentSn == null ? null : departmentSn.trim();
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post == null ? null : post.trim();
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
}