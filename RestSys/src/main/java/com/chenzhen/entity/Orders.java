package com.chenzhen.entity;

public class Orders {

    private  int id;//订单id
    private  String code;//订单编号
    private String data;//订单创建时间
    private int desk_id;//餐桌id
    private Desk desk;//餐桌信息
    private int count;//总数量
    private float amount;//总价
    private int status;//订单状态
    private String comments;//备注
    private int user_id;//用户id
    private User user;

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", data='" + data + '\'' +
                ", desk_id=" + desk_id +
                ", desk=" + desk +
                ", count=" + count +
                ", amount=" + amount +
                ", status=" + status +
                ", comments='" + comments + '\'' +
                ", user_id=" + user_id +
                ", user=" + user +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Desk getDesk() {
        return desk;
    }

    public void setDesk(Desk desk) {
        this.desk = desk;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getDesk_id() {
        return desk_id;
    }

    public void setDesk_id(int desk_id) {
        this.desk_id = desk_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Orders() {
    }

    public Orders(int id, String code, String data, int desk_id, Desk desk, int count, float amount, int status, String comments, int user_id) {
        this.id = id;
        this.code = code;
        this.data = data;
        this.desk_id = desk_id;
        this.desk = desk;
        this.count = count;
        this.amount = amount;
        this.status = status;
        this.comments = comments;
        this.user_id = user_id;
    }

    public Orders(int id, String code, String data, int desk_id, int count, float amount, int status, String comments, int user_id) {
        this.id = id;
        this.code = code;
        this.data = data;
        this.desk_id = desk_id;
        this.count = count;
        this.amount = amount;
        this.status = status;
        this.comments = comments;
        this.user_id = user_id;
    }
}
