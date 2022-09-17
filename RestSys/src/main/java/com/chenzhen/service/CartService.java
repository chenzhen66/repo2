package com.chenzhen.service;

import com.chenzhen.entity.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartService {

    public List<Cart> findAll();

    public int insert(Cart cart);

    public List<Cart> findByUserId(int user_id);

    public List<Cart> findByUserAndFoodId(Cart cart);

    public int updateCountByUserAndFoodId(Cart cart);

    public int deleteById(List<String> list);

    public List<Cart> findById(@Param("cart_id") int cart_id);
}
