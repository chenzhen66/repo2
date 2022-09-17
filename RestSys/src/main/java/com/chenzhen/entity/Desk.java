package com.chenzhen.entity;

public class Desk {

    private int id;//餐台id
    private String name;//桌号
    private int status;//占用状态
    private int desktype_id;//餐台类型id
    private int user_id;//用户id
    private DeskType deskType;//餐台类型
    private User user;//用户

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DeskType getDeskType() {
        return deskType;
    }

    public void setDeskType(DeskType deskType) {
        this.deskType = deskType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDesktype_id() {
        return desktype_id;
    }

    public void setDesktype_id(int desktype_id) {
        this.desktype_id = desktype_id;
    }

    @Override
    public String toString() {
        return "Desk{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", desktype_id=" + desktype_id +
                ", user_id=" + user_id +
                ", deskType=" + deskType +
                ", user=" + user +
                '}';
    }

}
