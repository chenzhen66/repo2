package com.chenzhen.service;

import com.chenzhen.entity.User;
import org.apache.ibatis.annotations.Param;

import java.io.IOException;
import java.util.List;

public interface UserService {

    public List<User> findByMail(String mail);
    public int insert(User user);

    public List<User> findAll();

    public int update(User user);

    public int updatamsg(User user);

    public User findById(int id);

    public User login(User user);

}
