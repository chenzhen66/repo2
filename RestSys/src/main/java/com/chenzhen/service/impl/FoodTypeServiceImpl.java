package com.chenzhen.service.impl;

import com.chenzhen.entity.FoodType;
import com.chenzhen.mapper.FoodTypeMapper;
import com.chenzhen.service.FoodTypeService;
import com.chenzhen.utils.sqlUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class FoodTypeServiceImpl implements FoodTypeService {
    @Override
    public List<FoodType> findAll() {
        SqlSession sqlSession = sqlUtils.conn();
        FoodTypeMapper mapper = sqlSession.getMapper(FoodTypeMapper.class);
        List<FoodType> list = mapper.findAll();
        sqlSession.close();
        return list;
    }

    @Override
    public List<FoodType> findByName(String tname) {
        SqlSession sqlSession = sqlUtils.conn();
        FoodTypeMapper mapper = sqlSession.getMapper(FoodTypeMapper.class);
        List<FoodType> list = mapper.findByName(tname);
        sqlSession.close();
        return list;
    }

    @Override
    public int addFoodType(FoodType foodType) {
        SqlSession sqlSession = sqlUtils.conn();
        FoodTypeMapper mapper = sqlSession.getMapper(FoodTypeMapper.class);
        int res = mapper.addFoodType(foodType);
        sqlSession.close();
        return res;
    }

    @Override
    public List<FoodType> findById(int id) {
        SqlSession sqlSession = sqlUtils.conn();
        FoodTypeMapper mapper = sqlSession.getMapper(FoodTypeMapper.class);
        List<FoodType> list = mapper.findById(id);
        sqlSession.close();
        return list;
    }

    @Override
    public List<FoodType> findById(List<Integer> ids) {
        List<FoodType> list = new ArrayList<>();
        SqlSession sqlSession = sqlUtils.conn();
        FoodTypeMapper mapper = sqlSession.getMapper(FoodTypeMapper.class);
        for (int id:ids) {
            list.addAll(mapper.findById(id));
        }
        sqlSession.close();
        return list;
    }

    @Override
    public int updatefoodtype(FoodType foodType) {
        SqlSession sqlSession = sqlUtils.conn();
        FoodTypeMapper mapper = sqlSession.getMapper(FoodTypeMapper.class);
        int res = mapper.updatefoodtype(foodType);
        sqlSession.close();
        return res;
    }

    @Override
    public int deleteFoodTypeById(int id) {
        SqlSession sqlSession = sqlUtils.conn();
        FoodTypeMapper mapper = sqlSession.getMapper(FoodTypeMapper.class);
        int res = mapper.deleteFoodTypeById(id);
        sqlSession.close();
        return res;
    }
}
