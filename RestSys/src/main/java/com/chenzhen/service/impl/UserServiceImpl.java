package com.chenzhen.service.impl;

import com.chenzhen.entity.User;
import com.chenzhen.mapper.UserMapper;
import com.chenzhen.service.UserService;
import com.chenzhen.utils.sqlUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public List<User> findByMail(String mail) {
       SqlSession sqlSession = sqlUtils.conn();
       UserMapper mapper = sqlSession.getMapper(UserMapper.class);
       List<User> list = mapper.findByMail(mail);
       sqlSession.close();
       return list;
    }

    @Override
    public int insert(User user) {
        SqlSession sqlSession = sqlUtils.conn();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int id =mapper.insert(user);
        sqlSession.close();
        return id;
    }

    @Override
    public List<User> findAll(){
        SqlSession sqlSession = sqlUtils.conn();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = null;
        try {
            list = mapper.findAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSession.close();
        return list;
    }

    @Override
    public int update(User user) {
        SqlSession sqlSession = sqlUtils.conn();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int result = mapper.updata(user);
        sqlSession.close();
        return result;
    }

    @Override
    public int updatamsg(User user) {
        SqlSession sqlSession = sqlUtils.conn();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int result = mapper.updatamsg(user);
        sqlSession.close();
        return result;
    }

    @Override
    public User findById(int id) {
        SqlSession sqlSession = sqlUtils.conn();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.findById(1);
        sqlSession.close();
        return user;
    }

    @Override
    public User login(User user) {
        SqlSession sqlSession = sqlUtils.conn();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user1 = mapper.login(user);
        sqlSession.close();
        return user1;
    }
}
