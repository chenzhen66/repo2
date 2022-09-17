package com.chenzhen.entity;

public class Admin {
    private int admin_id;//管理员id
    private String admin_num;//管理员账号
    private String password;//管理员密码

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_num() {
        return admin_num;
    }

    public void setAdmin_num(String admin_num) {
        this.admin_num = admin_num;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "admin_id=" + admin_id +
                ", admin_num='" + admin_num + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
