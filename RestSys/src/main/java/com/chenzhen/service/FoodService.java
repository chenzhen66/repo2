package com.chenzhen.service;

import com.chenzhen.entity.Food;
import com.chenzhen.entity.FoodType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FoodService {

    public List<Food> findAll();

    public List<Food> findByName(String name);

    public int addFood(Food food);

    public List<Food> findById(int id);

    public int updateFood(Food food);

    public List<Food> findByTypeId(int foodtype_id);

    public int deleteFoodById(int id);
}
