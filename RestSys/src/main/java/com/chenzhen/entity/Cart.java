package com.chenzhen.entity;

public class Cart {

    private int cart_id;//购物车ID
    private int food_id;//菜品id
    private int count;//数量
    private Food food;//菜品信息
    private int desk_id;//餐台id
    private Desk desk;//餐台信息
    private int user_id;//用户id
    private User user;//用户信息

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getDesk_id() {
        return desk_id;
    }

    public void setDesk_id(int desk_id) {
        this.desk_id = desk_id;
    }

    public Desk getDesk() {
        return desk;
    }

    public void setDesk(Desk desk) {
        this.desk = desk;
    }

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

    @Override
    public String toString() {
        return "Cart{" +
                "cart_id=" + cart_id +
                ", food_id=" + food_id +
                ", count=" + count +
                ", food=" + food +
                ", desk_id=" + desk_id +
                ", desk=" + desk +
                ", user_id=" + user_id +
                ", user=" + user +
                '}';
    }

    public Cart() {
        this.cart_id = cart_id;
        this.food_id = food_id;
        this.count = count;
        this.food = food;
        this.desk_id = desk_id;
        this.desk = desk;
        this.user_id = user_id;
        this.user = user;
    }
}
