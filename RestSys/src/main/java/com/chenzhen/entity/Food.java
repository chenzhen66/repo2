package com.chenzhen.entity;

public class Food {

    private int id; //餐品id
    private String name;//餐品名称
    private String specifications;//餐品分量
    private float price;//餐品价格
    private String imgpath;//图片路径
    private int foodtype_id;//餐品类型id
    private FoodType foodType;//食物类型

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

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public int getFoodtype_id() {
        return foodtype_id;
    }

    public void setFoodtype_id(int foodtype_id) {
        this.foodtype_id = foodtype_id;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specifications='" + specifications + '\'' +
                ", price=" + price +
                ", imgpath='" + imgpath + '\'' +
                ", foodtype_id=" + foodtype_id +
                ", foodType=" + foodType +
                '}';
    }
}
