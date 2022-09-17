package com.chenzhen.service;

import com.chenzhen.entity.FoodType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FoodTypeService {

    public List<FoodType> findAll();

    public List<FoodType> findByName(String tname);

    public int addFoodType(FoodType foodType);

    public List<FoodType> findById(int id);

    List<FoodType> findById(List<Integer> ids);

    public int updatefoodtype(FoodType foodType);

    public int deleteFoodTypeById(@Param("id") int id);

}
