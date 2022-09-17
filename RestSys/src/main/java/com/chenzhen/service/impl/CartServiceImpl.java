package com.chenzhen.service.impl;

import com.chenzhen.entity.Cart;
import com.chenzhen.mapper.CartMapper;
import com.chenzhen.service.CartService;
import com.chenzhen.utils.sqlUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CartServiceImpl implements CartService {
    @Override
    public List<Cart> findAll() {
        SqlSession sqlSession = sqlUtils.conn();
        CartMapper mapper = sqlSession.getMapper(CartMapper.class);
        List<Cart> list = mapper.findAll();
        sqlSession.close();
        return list;
    }

    @Override
    public int insert(Cart cart) {
        SqlSession sqlSession = sqlUtils.conn();
        CartMapper mapper = sqlSession.getMapper(CartMapper.class);
        int res = mapper.insert(cart);
        sqlSession.close();
        return res;
    }

    @Override
    public List<Cart> findByUserId(int user_id) {
        SqlSession sqlSession = sqlUtils.conn();
        CartMapper mapper = sqlSession.getMapper(CartMapper.class);
        List<Cart> list = mapper.findByUserId(user_id);
        sqlSession.close();
        return list;
    }

    @Override
    public List<Cart> findByUserAndFoodId(Cart cart) {
        SqlSession sqlSession = sqlUtils.conn();
        CartMapper mapper = sqlSession.getMapper(CartMapper.class);
        List<Cart> list = mapper.findByUserAndFoodId(cart);
        sqlSession.close();
        return list;
    }

    @Override
    public int updateCountByUserAndFoodId(Cart cart) {
        SqlSession sqlSession = sqlUtils.conn();
        CartMapper mapper = sqlSession.getMapper(CartMapper.class);
        int res = mapper.updateCountByUserAndFoodId(cart);
        sqlSession.close();
        return res;
    }

    @Override
    public int deleteById(List<String> list) {
        SqlSession sqlSession = sqlUtils.conn();
        CartMapper cartMapper = sqlSession.getMapper(CartMapper.class);
        int result=0;
        for (String list1:list){
            int test = cartMapper.deleteById(Integer.parseInt(list1));
            if (test != 0) {
                result=test;
            }
        }
        sqlSession.close();
        return result;
    }

    @Override
    public List<Cart> findById(int cart_id) {
        SqlSession sqlSession = sqlUtils.conn();
        CartMapper mapper = sqlSession.getMapper(CartMapper.class);
        List<Cart> list = mapper.findById(cart_id);
        sqlSession.close();
        return list;
    }
}
