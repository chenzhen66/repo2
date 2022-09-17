package com.chenzhen.mapper;

import com.chenzhen.entity.Code;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CodeMapper {

    /*
    * 添加一条验证码信息
    * */
    @Insert("insert into `tb-verification` values(null,'${code}','${date}',1)")
    int addCode(Code code);

    /*
    * 查询验证码
    * */
    @Select("select * from `tb-verification` where code='${code}' and user_id='${user_id}' ")
    List<Code> findcodeByuseridandcode(Code code);
}
