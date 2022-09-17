package com.chenzhen.mapper;

import com.chenzhen.entity.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.javassist.expr.Cast;

import java.util.List;

public interface CartMapper {

    /*
    * 查询购物车中所有信息
    * */
    @Select("SELECT * from tb_cart")
    @Results({
            @Result(column = "cart_id",property = "cart_id"),
            @Result(
                    column = "food_id",
                    property = "food",
                    javaType = Food.class,
                    one = @One(select = "com.chenzhen.mapper.FoodMapper.findById")
            ),
            @Result(
                    column = "desk_id",
                    property = "desk",
                    javaType = Desk.class,
                    one = @One(select = "com.chenzhen.mapper.DeskMapper.findById")
            ), @Result(
                    column = "user_id",
                    property = "user",
                    javaType = User.class,
                    one = @One(select = "com.chenzhen.mapper.UserMapper.findById")
            )
    })
    public List<Cart> findAll();

    /*
    * 根据用户id查询对应的购物车信息
    * */
    @Select("SELECT * from tb_cart where user_id=${user_id}")
    @Results({
            @Result(column = "cart_id",property = "cart_id"),
            @Result(
                    column = "food_id",
                    property = "food",
                    javaType = Food.class,
                    one = @One(select = "com.chenzhen.mapper.FoodMapper.findById")
            ),
            @Result(
                    column = "desk_id",
                    property = "desk",
                    javaType = Desk.class,
                    one = @One(select = "com.chenzhen.mapper.DeskMapper.findById")
            ), @Result(
            column = "user_id",
            property = "user",
            javaType = User.class,
            one = @One(select = "com.chenzhen.mapper.UserMapper.findById")
    )
    })
    public List<Cart> findByUserId(@Param("user_id") int user_id);


    /*
     * 根据id查询对应的购物车信息
     * */
    @Select("SELECT * from tb_cart where cart_id=${cart_id}")
    @Results({
            @Result(column = "cart_id",property = "cart_id"),
            @Result(
                    column = "food_id",
                    property = "food",
                    javaType = Food.class,
                    one = @One(select = "com.chenzhen.mapper.FoodMapper.findById")
            ),
            @Result(
                    column = "desk_id",
                    property = "desk",
                    javaType = Desk.class,
                    one = @One(select = "com.chenzhen.mapper.DeskMapper.findById")
            ), @Result(
            column = "user_id",
            property = "user",
            javaType = User.class,
            one = @One(select = "com.chenzhen.mapper.UserMapper.findById")
    )
    })
    public List<Cart> findById(@Param("cart_id") int cart_id);

    /*
    * 根据餐品id和用户id查询购物车信息
    * */
    @Select("SELECT * from tb_cart where user_id=${user_id} and food_id=${food_id}")
    @Results({
            @Result(column = "cart_id",property = "cart_id"),
            @Result(
                    column = "food_id",
                    property = "food",
                    javaType = Food.class,
                    one = @One(select = "com.chenzhen.mapper.FoodMapper.findById")
            ),
            @Result(
                    column = "desk_id",
                    property = "desk",
                    javaType = Desk.class,
                    one = @One(select = "com.chenzhen.mapper.DeskMapper.findById")
            ),
            @Result(
            column = "user_id",
            property = "user",
            javaType = User.class,
            one = @One(select = "com.chenzhen.mapper.UserMapper.findById")
    )
    })
    public List<Cart> findByUserAndFoodId(Cart cart);

    /*
    * 添加购物车信息，点餐
    * */
    @Insert("insert into tb_cart value(null,${count},${food_id},${desk_id},${user_id})")
    public int insert(Cart cart);

    /*
    * 修改购买单个餐品的数量
    * */
    @Update("UPDATE tb_cart SET count=${count} WHERE user_id=${user_id} and food_id=${food_id}")
    public int updateCountByUserAndFoodId(Cart cart);

    /*
    * 删除餐品
    * */
    @Delete("DELETE FROM tb_cart WHERE cart_id=#{cart_id}")
    public int deleteById(int cart_id);

}
