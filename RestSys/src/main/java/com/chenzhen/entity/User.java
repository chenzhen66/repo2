package com.chenzhen.entity;

public class User {

    int user_id;//用户id
    String mail;//用户的邮箱
    String password;//用户的密码
    String user_name;//用户的姓名
    String sex;//用户性别

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", user_name='" + user_name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
