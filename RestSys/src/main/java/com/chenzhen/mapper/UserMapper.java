package com.chenzhen.mapper;

import com.chenzhen.entity.User;
import org.apache.ibatis.annotations.*;

import java.io.IOException;
import java.util.List;

public interface UserMapper {


    @Select("select * from tb_user")
    public List<User> findAll() throws IOException;

    @Select("select * from tb_user where user_id=#{user_id}")
    public User findById(int id);

    @Select("select * from tb_user where mail='${mail}'")
    public List<User> findByMail(@Param("mail") String mail);

    @Insert("insert into tb_user values(null,'${mail}','${password}','${user_name}','${sex}')")
    public int insert(User user);

    @Update("update tb_user set password='${password}' where user_id=#{user_id}")
    public int updata(User user);

    @Update("update tb_user set user_name='${user_name}',sex='${sex}' where user_id=#{user_id}")
    public int updatamsg(User user);


    @Select("select * from tb_user where mail='${mail}' and password='${password}'")
    public User login(User user);


}
