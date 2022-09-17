package com.chenzhen.mapper;

import com.chenzhen.entity.Food;
import com.chenzhen.entity.FoodType;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface FoodMapper {

    @Select("select * from tb_food")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "price",property = "price"),
            @Result(
                    column = "foodtype_id",
                    property = "foodType",
                    javaType = FoodType.class,
                    one = @One(select = "com.chenzhen.mapper.FoodTypeMapper.findById")
            )
    })
    public List<Food> findAll();

    @Select("select * from tb_food where name like '%${name}%'")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "price",property = "price"),
            @Result(column = "imgpath",property = "imgpath"),
            @Result(
                    column = "foodtype_id",
                    property = "foodType",
                    javaType = FoodType.class,
                    one = @One(select = "com.chenzhen.mapper.FoodTypeMapper.findById")
            )
    })
    public List<Food> findByName(@Param("name") String name);

    @Select("select * from tb_food where id=${id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "price",property = "price"),
            @Result(column = "imgpath",property = "imgpath"),
            @Result(
                    column = "foodtype_id",
                    property = "foodType",
                    javaType = FoodType.class,
                    one = @One(select = "com.chenzhen.mapper.FoodTypeMapper.findById")
            )
    })
    public List<Food> findById(@Param("id") int id);

    @Select("select * from tb_food where foodtype_id=${foodtype_id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "price",property = "price"),
            @Result(column = "imgpath",property = "imgpath"),
            @Result(
                    column = "foodtype_id",
                    property = "foodType",
                    javaType = FoodType.class,
                    one = @One(select = "com.chenzhen.mapper.FoodTypeMapper.findById")
            )
    })
    public List<Food> findByTypeId(@Param("foodtype_id") int foodtype_id);

    @Insert("insert into tb_food values(null,'${name}',${price},${foodtype_id},'${imgpath}')")
    public int addFood(Food food);

    @Update("update tb_food set name='${name}',price=${price},foodtype_id=${foodtype_id},imgpath='${imgpath}' where id=${id}")
    public int updateFood(Food food);

    @Delete("delete from tb_food where id=${id}")
    public int deleteFoodById(int id);

}
