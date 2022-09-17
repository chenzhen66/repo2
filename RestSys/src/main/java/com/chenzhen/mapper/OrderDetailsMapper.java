package com.chenzhen.mapper;

import com.chenzhen.entity.Food;
import com.chenzhen.entity.OrderDetails;
import com.chenzhen.entity.Orders;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrderDetailsMapper {

    @Insert("INSERT INTO tb_orderdetails VALUES(NULL,'${order_id}','${food_id}','${count}','${user_id}','${status}');")
    int addOrderDetails(OrderDetails orderDetails);

    @Select("SELECT * FROM tb_orderdetails WHERE order_id='${order_id}'")
    @Results({
            @Result(
                    column = "food_id",
                    property = "food",
                    javaType = Food.class,
                    one = @One(select = "com.chenzhen.mapper.FoodMapper.findById")
            ),
            @Result(
                    column = "order_id",
                    property = "orders",
                    javaType = Orders.class,
                    one = @One(select = "com.chenzhen.mapper.OrderMapper.FindAllByCode")
            )
    })
    List<OrderDetails> findOrderDetailsByCode(String code);

    @Update("update tb_orderdetails set status='${status}' where order_id='${code}';")
    int updateOrderDetailstatus(@Param("status") String status,@Param("code") String code);

    @Update("update tb_orderdetails set status='${status}' where id='${id}';")
    int updateOrderDetailstatusById(@Param("status") int status,@Param("id")int id);
    @Delete("delete from tb_orderdetails where id=#{id}")
    int deleteById(@Param("id") int id);
}
