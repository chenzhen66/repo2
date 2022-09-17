package com.chenzhen.entity;

public class OrderDetails {

    private int id;//订单详情id
    private String order_id;//订单id同Order表中Code相同
    private Orders orders;//orders实体
    private int food_id;//餐品id
    private Food food;//餐品信息
    private int count;//餐品购买数量
    private int user_id;//购买人id

    private int status;


    @Override
    public String toString() {
        return "OrderDetails{" +
                "id=" + id +
                ", order_id='" + order_id + '\'' +
                ", orders=" + orders +
                ", food_id=" + food_id +
                ", food=" + food +
                ", count=" + count +
                ", user_id=" + user_id +
                ", status=" + status +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public OrderDetails(int id, String order_id, Orders orders, int food_id, Food food, int count, int user_id, int status) {
        this.id = id;
        this.order_id = order_id;
        this.orders = orders;
        this.food_id = food_id;
        this.food = food;
        this.count = count;
        this.user_id = user_id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public OrderDetails() {
    }

    public OrderDetails(int id, String order_id, Orders orders, int food_id, Food food, int count, int user_id) {
        this.id = id;
        this.order_id = order_id;
        this.orders = orders;
        this.food_id = food_id;
        this.food = food;
        this.count = count;
        this.user_id = user_id;
    }
}
