package com.chenzhen;

import com.chenzhen.entity.Cart;
import com.chenzhen.entity.User;
import com.chenzhen.mapper.CartMapper;
import com.chenzhen.mapper.UserMapper;
import com.chenzhen.service.CartService;
import com.chenzhen.service.UserService;
import com.chenzhen.service.impl.CartServiceImpl;
import com.chenzhen.service.impl.UserServiceImpl;
import com.chenzhen.utils.MD5Utils;
import com.chenzhen.utils.sqlUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MyBatisTest {

    @Test
    public void  test8(){
        User user = new User();
        user.setUser_id(1);
        String a= "1111";
        String dm5 = MD5Utils.MD5(a);
        user.setPassword(dm5);
        UserService userService = new UserServiceImpl();
        userService.update(user);
    }

    @Test
    public  void  test7(){
        CartService cartService =new CartServiceImpl();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
    }

    @Test
    public void text6(){
        SqlSession sqlSession = sqlUtils.conn();
        CartMapper mapper = sqlSession.getMapper(CartMapper.class);
        Cart cart = new Cart();
        cart.setCart_id(62);
        int a = mapper.deleteById(0);
        System.out.println(a);
        sqlSession.close();
    }

    @Test
    public  void  test5() throws IOException {
        SqlSession sqlSession = sqlUtils.conn();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = mapper.findAll();
        Logger logger = Logger.getLogger(MyBatisTest.class);
        logger.info("User:"+list);
    }
    //查询测试
    @Test
    public void test1() throws IOException {
        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行操作 参数，namespace+id
        List<User> userList = sqlSession.selectList("usermapper.findAll");
        //打印数据
        System.out.println(userList);
        //释放资源
        sqlSession.close();
    }
    //增加测试
    @Test
    public void test2() throws IOException {
        User user = new User();
        user.setMail("hhaha");
        user.setPassword("12345");
        user.setUser_name("振振");
        user.setSex("男");
        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行操作 参数，namespace+id
        sqlSession.insert("userMapper.save",user);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    //更新测试
    @Test
    public void test3() throws IOException {
        User user = new User();
        user.setUser_id(2);
        user.setPassword("54321");
        user.setUser_name("振振");
        user.setSex("男");
        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行操作 参数，namespace+id
        sqlSession.update("userMapper.update",user);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    //删除测试
    @Test
    public void test4() throws IOException {
        User user = new User();
        user.setUser_id(2);

        user.setPassword("54321");
        user.setUser_name("振振");
        user.setSex("男");
        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行操作 参数，namespace+id
        sqlSession.delete("userMapper.dele",2);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }


}
