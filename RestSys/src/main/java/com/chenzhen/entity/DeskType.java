package com.chenzhen.entity;

public class DeskType {

    private int dp_id; //餐台id
    private String dp_name;//餐台类型
    private int pnum;//餐台最大人数

    public int getDp_id() {
        return dp_id;
    }

    public void setDp_id(int dp_id) {
        this.dp_id = dp_id;
    }

    public String getDp_name() {
        return dp_name;
    }

    public void setDp_name(String dp_name) {
        this.dp_name = dp_name;
    }

    public int getPnum() {
        return pnum;
    }

    public void setPnum(int pnum) {
        this.pnum = pnum;
    }

    @Override
    public String toString() {
        return "DeskType{" +
                "dp_id=" + dp_id +
                ", dp_name='" + dp_name + '\'' +
                ", pnum=" + pnum +
                '}';
    }
}
