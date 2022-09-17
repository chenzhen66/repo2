package com.chenzhen.mapper;

import com.chenzhen.entity.Desk;
import com.chenzhen.entity.DeskType;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface DeskMapper {

    /*
    * 查询所有餐桌信息
    * */
    @Select("select * from tb_desk")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "status",property = "status"),
            @Result(
                    column = "desktype_id",
                    property = "deskType",
                    javaType = DeskType.class,
                    one = @One(select = "com.chenzhen.mapper.DeskTypeMapper.findByid")
            ),
            @Result(
                    column = "user_id",
                    property = "user",
                    javaType = DeskType.class,
                    one = @One(select = "com.chenzhen.mapper.UserMapper.findById")
            )
    })
    public List<Desk> findAll();

    /*
    * 查询用户已经占座的餐桌信息
    * */
    @Select("select * from tb_desk where user_id='${user_id}'")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "status",property = "status"),
            @Result(
                    column = "desktype_id",
                    property = "deskType",
                    javaType = DeskType.class,
                    one = @One(select = "com.chenzhen.mapper.DeskTypeMapper.findByid")
            ),
            @Result(
                    column = "user_id",
                    property = "user",
                    javaType = DeskType.class,
                    one = @One(select = "com.chenzhen.mapper.UserMapper.findById")
            )
    })
    public List<Desk> findByUserid(@Param("user_id") int user_id);

    /*
    * 根据餐桌名称模糊搜索
    * */
    @Select("select * from tb_desk where name like '%${name}%'")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "status",property = "status"),
            @Result(
                    column = "desktype_id",
                    property = "deskType",
                    javaType = DeskType.class,
                    one = @One(select = "com.chenzhen.mapper.DeskTypeMapper.findByid")
            ),
            @Result(
                    column = "user_id",
                    property = "user",
                    javaType = DeskType.class,
                    one = @One(select = "com.chenzhen.mapper.UserMapper.findById")
            )
    })
    public List<Desk> searchDesk(@Param("name") String name);

    /*
    * 查询单个餐桌信息，用于修改信息回显
    * */
    @Select("select * from tb_desk where id=${id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "status",property = "status"),
            @Result(
                    column = "desktype_id",
                    property = "deskType",
                    javaType = DeskType.class,
                    one = @One(select = "com.chenzhen.mapper.DeskTypeMapper.findByid")
            ),
            @Result(
                    column = "user_id",
                    property = "user",
                    javaType = DeskType.class,
                    one = @One(select = "com.chenzhen.mapper.UserMapper.findById")
            )
    })
    public List<Desk> findById(@Param("id") int id);

    /*
    * 查询所有餐桌类型信息
    * */
    @Select("select * from tb_desktype")
    public List<DeskType> finddesktype();

    /*
    * 添加条餐桌信息
    * */
    @Insert("INSERT INTO tb_desk VALUES(NULL,#{name},0,${desktype_id},null)")
    public int insertDesk(Desk desk);

    /*
    * 修改餐桌使用状态，占座
    * */
    @Update("update tb_desk set status=${status},user_id=${user_id} where id=${id}")
    public int updatestatus(Desk desk);

    /*
    * 删除餐桌信息
    * */
    @Delete("delete from tb_desk where id=${id}")
    public int deleteDeskById(int id);

    /*
    * 修改餐桌信息
    * */
    @Update("update tb_desk set name='${name}',desktype_id=${desktype_id} where id=${id}")
    public int updateDeskById(Desk desk);

}
