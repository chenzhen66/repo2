package com.chenzhen.service.impl;

import com.chenzhen.entity.OrderDetails;
import com.chenzhen.entity.Orders;
import com.chenzhen.mapper.OrderDetailsMapper;
import com.chenzhen.mapper.OrderMapper;
import com.chenzhen.service.OrderService;
import com.chenzhen.utils.sqlUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Override
    public int AddOrder(Orders order) {
        Logger logger = Logger.getLogger(OrderServiceImpl.class);
        logger.info("sql");
        SqlSession sqlSession = sqlUtils.conn();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        int result = mapper.AddOrder(order);
        logger.info("finuily:");
        sqlSession.close();
        return result;
    }

    @Override
    public int addOrderDetails(OrderDetails orderDetails) {
        SqlSession sqlSession = sqlUtils.conn();
        OrderDetailsMapper mapper = sqlSession.getMapper(OrderDetailsMapper.class);
        int result = mapper.addOrderDetails(orderDetails);
        sqlSession.close();
        return result;
    }

    @Override
    public List<Orders> FindAllByUserId(int user_id) {
        SqlSession sqlSession = sqlUtils.conn();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        List<Orders> list = mapper.FindAllByUserId(user_id);
        sqlSession.close();
        return list;
    }

    @Override
    public List<OrderDetails> findOrderDetailsByCode(String code) {
        SqlSession sqlSession = sqlUtils.conn();
        OrderDetailsMapper mapper = sqlSession.getMapper(OrderDetailsMapper.class);
        List<OrderDetails> list = mapper.findOrderDetailsByCode(code);
        sqlSession.close();
        return list;
    }

    @Override
    public List<Orders> findAll() {
        SqlSession sqlSession = sqlUtils.conn();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        List<Orders> list = mapper.findAll();
        sqlSession.close();
        return list;
    }

    @Override
    public List<Orders> findByStatusAndUserId(int user_id, int status) {
        SqlSession sqlSession = sqlUtils.conn();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        List<Orders> list = mapper.findByStatusAndUserId(user_id,status);
        sqlSession.close();
        return list;
    }

    @Override
    public int updatestatus(String status,String code) {
        SqlSession sqlSession = sqlUtils.conn();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        int result = mapper.updatestatus(status,code);
        sqlSession.close();
        return result;
    }

    @Override
    public int updateOrderDetailstatus(String status,String code) {
        SqlSession sqlSession = sqlUtils.conn();
        OrderDetailsMapper mapper = sqlSession.getMapper(OrderDetailsMapper.class);
        int result = mapper.updateOrderDetailstatus(status,code);
        sqlSession.close();
        return result;
    }

    @Override
    public List<Orders> FindByDate(String tdate, String edate, int user_id) {
        SqlSession sqlSession = sqlUtils.conn();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        List<Orders> list = mapper.FindByDate(tdate,edate,user_id);
        sqlSession.close();
        return list;
    }

    @Override
    public List<Orders> findByDeskidAndUserid(int desk_id, int user_id) {
        SqlSession sqlSession = sqlUtils.conn();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        List<Orders> list = mapper.findByDeskidAndUserid(desk_id,user_id);
        sqlSession.close();
        return list;
    }

    @Override
    public int deleteOrderDetailsById(int id) {
        SqlSession sqlSession = sqlUtils.conn();
        OrderDetailsMapper mapper = sqlSession.getMapper(OrderDetailsMapper.class);
        int result = mapper.deleteById(id);
        sqlSession.close();
        return result;
    }

    @Override
    public int UpdeteOrder(Orders orders) {
        SqlSession sqlSession = sqlUtils.conn();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        int result = mapper.UpdeteOrder(orders);
        System.out.println("更新执行了"+orders);
        sqlSession.close();
        return result;
    }

    @Override
    public int deleteOrderBycode(String code) {
        SqlSession sqlSession = sqlUtils.conn();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        int result = mapper.deleteOrderBycode(code);
        sqlSession.close();
        return result;
    }

    @Override
    public int updateOrderDetailstatusById(int status, int id) {
        SqlSession sqlSession = sqlUtils.conn();
        OrderDetailsMapper mapper = sqlSession.getMapper(OrderDetailsMapper.class);
        int result = mapper.updateOrderDetailstatusById(status,id);
        sqlSession.close();
        return result;
    }

    @Override
    public List<Orders> findAllByDeskid(String desk_id) {
        SqlSession sqlSession = sqlUtils.conn();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        List<Orders> list = mapper.findAllByDeskid(desk_id);
        sqlSession.close();
        return list;
    }

    @Override
    public List<Orders> findAllByDeskidfinish(String deskid) {
        SqlSession sqlSession = sqlUtils.conn();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        List<Orders> list = mapper.findAllByDeskidfinish(deskid);
        sqlSession.close();
        return list;
    }
}
