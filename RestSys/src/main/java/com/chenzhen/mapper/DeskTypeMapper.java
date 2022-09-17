package com.chenzhen.mapper;

import com.chenzhen.entity.DeskType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.io.IOException;
import java.util.List;

public interface DeskTypeMapper {

    /*
    *
    * */
    @Insert("INSERT INTO tb_desktype VALUES(NULL,#{dp_name},${pnum})")
    public int insert(DeskType deskType);

    @Select("select * from tb_desktype")
    public List<DeskType> findAll();

    @Select("select * from tb_desktype where dp_name like '%${pnum}%'")
    public List<DeskType> findByname(String name);

    @Select("select * from tb_desktype where dp_id = ${dp_id}")
    public List<DeskType> findByid(int id);

    @Update("update tb_desktype set pnum=${pnum} where dp_id=${dp_id}")
    public int updatePer(DeskType deskType);

    @Update("update tb_desktype set dp_name='${dp_name}',pnum=${pnum} where dp_id=${dp_id}")
    public int updateDeskType(DeskType deskType);


    @Delete("delete from tb_desktype where dp_id=${dp_id}")
    public int deleteDeskTypeById(int id);


}
