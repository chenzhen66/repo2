package com.chenzhen.service;

import com.chenzhen.entity.DeskType;

import java.util.List;

public interface DeskTypeService {

    /*添加餐台*/
    public int insert(DeskType deskType);
    /*查询所有餐台类型数据*/
    public List<DeskType> findAll();
    /*根据id查询餐台类型数据*/
    public List<DeskType> findByname(String name);
    /*修改餐台最大人数*/
    public int updatePer(DeskType deskType);

    /*删除餐台*/
    public int deleteDeskTypeById(int id);

    public List<DeskType> findById(int id);

    public int updateDeskType(DeskType deskType);
}
