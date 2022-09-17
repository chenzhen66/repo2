package com.chenzhen.entity;

public class FoodType {
    private int id;//餐品类型id
    private String tname;//餐品类型

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    @Override
    public String toString() {
        return "FoodType{" +
                "id=" + id +
                ", tname='" + tname + '\'' +
                '}';
    }
}
