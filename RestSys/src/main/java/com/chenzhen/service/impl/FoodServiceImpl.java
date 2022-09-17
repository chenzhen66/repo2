package com.chenzhen.service.impl;

import com.chenzhen.entity.Food;
import com.chenzhen.entity.FoodType;
import com.chenzhen.mapper.FoodMapper;
import com.chenzhen.service.FoodService;
import com.chenzhen.utils.sqlUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class FoodServiceImpl implements FoodService {

    @Override
    public List<Food> findAll() {
        SqlSession sqlSession = sqlUtils.conn();
        FoodMapper foodMapper = sqlSession.getMapper(FoodMapper.class);
        List<Food> list = foodMapper.findAll();
        sqlSession.close();
        return list;
    }

    @Override
    public List<Food> findByName(String name) {
        SqlSession sqlSession = sqlUtils.conn();
        FoodMapper foodMapper = sqlSession.getMapper(FoodMapper.class);
        List<Food> list = foodMapper.findByName(name);
        sqlSession.close();
        return list;
    }

    @Override
    public int addFood(Food food) {
        SqlSession sqlSession = sqlUtils.conn();
        FoodMapper foodMapper = sqlSession.getMapper(FoodMapper.class);
        int res = foodMapper.addFood(food);
        sqlSession.close();
        return res;
    }

    @Override
    public List<Food> findById(int id) {
        SqlSession sqlSession = sqlUtils.conn();
        FoodMapper foodMapper = sqlSession.getMapper(FoodMapper.class);
        List<Food> list = foodMapper.findById(id);
        sqlSession.close();
        return list;
    }

    @Override
    public int updateFood(Food food) {
        SqlSession sqlSession = sqlUtils.conn();
        FoodMapper foodMapper = sqlSession.getMapper(FoodMapper.class);
        int res = foodMapper.updateFood(food);
        sqlSession.close();
        return res;
    }

    @Override
    public List<Food> findByTypeId(int foodtype_id) {
        SqlSession sqlSession = sqlUtils.conn();
        FoodMapper foodMapper = sqlSession.getMapper(FoodMapper.class);
        List<Food> list = foodMapper.findByTypeId(foodtype_id);
        sqlSession.close();
        return list;
    }

    @Override
    public int deleteFoodById(int id) {
        SqlSession sqlSession = sqlUtils.conn();
        FoodMapper foodMapper = sqlSession.getMapper(FoodMapper.class);
        int res = foodMapper.deleteFoodById(id);
        sqlSession.close();
        return res;
    }
}
