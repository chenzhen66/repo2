package com.chenzhen.mapper;

import com.chenzhen.entity.Admin;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AdminMapper {

    /*
    * 根据管理员账号和密码查询管理员表中所有信息
    * */
    @Select("select * from tb_admin where admin_num='${admin_num}' and password='${password}'")
    public List<Admin> findbyuser(Admin admin);

    /*
    * 根据管理员id,更改管理员密码
    * */
    @Update("UPDATE tb_admin SET `password`='${password}' WHERE admin_id=${admin_id}")
    public int updatepsd(Admin admin);


}
