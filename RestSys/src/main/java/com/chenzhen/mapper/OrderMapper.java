package com.chenzhen.mapper;

import com.chenzhen.entity.Desk;
import com.chenzhen.entity.Food;
import com.chenzhen.entity.Orders;
import com.chenzhen.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrderMapper {

    @Insert("INSERT INTO tb_orders VALUES(NULL,'${code}','${data}','${desk_id}','${count}','${amount}','${status}','${comments}','${user_id}');")
    int AddOrder(Orders order);

    @Select("select * from tb_orders where user_id=${user_id}")
    @Results({
            @Result(column = "desk_id",property = "desk_id"),
            @Result(column = "data",property = "data"),
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
    List<Orders> FindAllByUserId(@Param("user_id") int user_id);

    @Select("select * from tb_orders where status=${status} and user_id=${user_id}")
    @Results({
            @Result(column = "desk_id",property = "desk_id"),
            @Result(column = "data",property = "data"),
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
    List<Orders> findByStatusAndUserId(@Param("user_id") int user_id,@Param("status") int status);


    @Select("select * from tb_orders where code='${code}'")
    @Results({
            @Result(column = "desk_id",property = "desk_id"),
            @Result(column = "data",property = "data"),
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
    List<Orders> FindAllByCode(String code);

    @Select("select * from tb_orders")
    @Results({
            @Result(column = "desk_id",property = "desk_id"),
            @Result(column = "data",property = "data"),
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
    List<Orders> findAll();

    @Update("update tb_orders set status='${status}' where code='${code}';")
    int updatestatus(@Param("status") String status,@Param("code") String code);

    @Select("SELECT * FROM `tb_orders` WHERE `data` BETWEEN '${tdate}' AND '${edate}' AND user_id='${user_id}'")
    @Results({
            @Result(column = "desk_id",property = "desk_id"),
            @Result(column = "data",property = "data"),
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
    List<Orders> FindByDate(@Param("tdate") String tdate,@Param("edate") String edate,@Param("user_id") int user_id);

    @Select("select * from tb_orders where desk_id='${desk_id}' and user_id='${user_id}' and status IN(0,1)")
    @Results({
            @Result(column = "desk_id",property = "desk_id"),
            @Result(column = "data",property = "data"),
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
    List<Orders> findByDeskidAndUserid(@Param("desk_id") int desk_id,@Param("user_id") int user_id);

    @Update("UPDATE tb_orders SET  count=count-${count},amount=amount-${amount} WHERE `code`='${code}'")
    int UpdeteOrder(Orders orders);

    @Delete("DELETE FROM tb_orders WHERE `code`='${code}'")
    int deleteOrderBycode(String code);

    @Select("select * from tb_orders where status<=1 and desk_id='${desk_id}'")
    @Results({
            @Result(column = "desk_id",property = "desk_id"),
            @Result(column = "data",property = "data"),
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
    List<Orders> findAllByDeskid(@Param("desk_id") String desk_id);

    @Select("select * from tb_orders where status>=2 and desk_id='${desk_id}'")
    @Results({
            @Result(column = "desk_id",property = "desk_id"),
            @Result(column = "data",property = "data"),
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
    List<Orders> findAllByDeskidfinish(String deskid);
}
