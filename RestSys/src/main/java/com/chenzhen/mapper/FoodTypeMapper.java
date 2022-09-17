package com.chenzhen.mapper;

import com.chenzhen.entity.FoodType;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface FoodTypeMapper {

    @Select("select * from tb_foodtype")
    public List<FoodType> findAll();

    @Select("select * from tb_foodtype where tname like '%${tname}%'")
    public List<FoodType> findByName(@Param("tname") String tname);

    @Select("select * from tb_foodtype where id=${id}")
    public List<FoodType> findById(@Param("id") int id);

    @Insert("insert into tb_foodtype values(null,'${tname}')")
    public int addFoodType(FoodType foodType);

    @Update("update tb_foodtype set tname='${tname}' where id=${id}")
    public int updatefoodtype(FoodType foodType);

    @Delete("delete from tb_foodtype where id=${id}")
    public int deleteFoodTypeById(@Param("id") int id);

}
