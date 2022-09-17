package com.chenzhen;

import com.chenzhen.entity.Code;
import com.chenzhen.entity.OrderDetails;
import com.chenzhen.mapper.CodeMapper;
import com.chenzhen.utils.sqlUtils;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataTest {

    @Test
    public void test3(){
        List<OrderDetails> list = new ArrayList<>();
        OrderDetails orderDetails = new OrderDetails();
        List<String> list1 = new ArrayList<>();
        list1.add("fkjksdfj");
        list1.add("hh");
        System.out.println(list1.indexOf("hh"));
    }
    @Test
    public void test2(){
        CodeMapper mapper = sqlUtils.conn().getMapper(CodeMapper.class);
        Code code = new Code();
        code.setCode("119722");
        code.setUser_id(1);
    }
    @Test
    public void randomtest(){
        int random = (int)((Math.random()*9+1)*100000);

        System.out.println(random);
    }
    @Test
    public void test1(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowdate = simpleDateFormat.format(date);
        System.out.println(date.getTime());
    }
}
