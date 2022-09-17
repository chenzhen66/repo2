package com.chenzhen.service;

import com.chenzhen.entity.OrderDetails;
import com.chenzhen.entity.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderService {

    int AddOrder(Orders order);

    int addOrderDetails(OrderDetails orderDetails);

    List<Orders> FindAllByUserId(int user_id);

    List<OrderDetails> findOrderDetailsByCode(String code);

    List<Orders> findAll();

    List<Orders> findByStatusAndUserId(int user_id,int status);

    int updatestatus(String status,String code);

    int updateOrderDetailstatus(String status,String code);

    List<Orders> FindByDate( String tdate,String edate,int user_id);

    List<Orders> findByDeskidAndUserid(int desk_id,int user_id);

    int deleteOrderDetailsById(int id);

    int UpdeteOrder(Orders orders);

    int deleteOrderBycode(@Param("code") String code);

    int updateOrderDetailstatusById(int status,int id);

    List<Orders> findAllByDeskid(String desk_id);

    List<Orders> findAllByDeskidfinish(String deskid);
}
