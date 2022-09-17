package com.chenzhen.Servlet;

import com.chenzhen.entity.Desk;
import com.chenzhen.entity.OrderDetails;
import com.chenzhen.entity.Orders;
import com.chenzhen.service.DeskService;
import com.chenzhen.service.OrderService;
import com.chenzhen.service.impl.DeskServiceImpl;
import com.chenzhen.service.impl.OrderServiceImpl;
import com.chenzhen.utils.BaseCalculate;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "AdminOrderServlet", value = "/AdminOrderServlet")
public class AdminOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html,charset=utf-8");
        String action = request.getParameter("action");
        if (action.equals("findAll")) {
            findAll(request,response);
        }else if (action.equals("findOrderdetilsByCode")) {
            findOrderdetilsByCode(request,response);
        }else if (action.equals("shangcai")) {
            shangcai(request,response);
        }else if (action.equals("Checkout")) {
            Checkout(request,response);
        }else if (action.equals("search")) {
            search(request,response);
        }

    }

    /*
    * 搜索订单
    * */
    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deskid = request.getParameter("deskid");
        String orderstatus = request.getParameter("orderstatus");
        OrderService orderService = new OrderServiceImpl();
        DeskService deskService = new DeskServiceImpl();
        List<Desk> desks = deskService.findAll();
        List<Orders> orders = new ArrayList<>();
        if (orderstatus.equals("ordering")) {
            orders = orderService.findAllByDeskid(deskid);
        }else {
            orders = orderService.findAllByDeskidfinish(deskid);
        }
        if (orders.size()==0||orders==null) {
            request.setAttribute("msg","alert('此座位没有人就餐')");
        }
        request.setAttribute("list",orders);
        request.setAttribute("desks",desks);
        request.getRequestDispatcher("admin/order/orders.jsp").forward(request,response);

    }

    /*更新订单状态*/
    private void Checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        String status = request.getParameter("status");
        OrderService orderService = new OrderServiceImpl();
        int a = orderService.updatestatus(status,code);
        int b = orderService.updateOrderDetailstatus(status,code);
        Logger logger = Logger.getLogger(this.getClass());
        if (a > 0) {
            logger.info("更新订单成功");
            if (b > 0) {
                logger.info("更新订单详情成功");
                if (status.equals("1")) {
                    request.setAttribute("msg","alert('已出餐!')");
                }else if (status.equals("3")){
                    request.setAttribute("msg","alert('订单已取消!')");
                }
            }else {
                request.setAttribute("msg","alert('失败!')");
            }
        }else {
            request.setAttribute("msg","alert('失败!')");
        }
        request.setAttribute("local","AdminOrderServlet?action=findAll");
        request.getRequestDispatcher("web/msg.jsp").forward(request,response);
    }

    /*
    * 上菜,总订单
    * */
    private void shangcai(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        OrderService orderService = new OrderServiceImpl();
        orderService.updateOrderDetailstatusById(2,id);
        request.setAttribute("msg","alert('已上菜')");
        findOrderdetilsByCode(request,response);
    }

    /*
    * 根据订单号查询订单详情
    * */
    private void findOrderdetilsByCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        OrderService orderService = new OrderServiceImpl();
        List<OrderDetails> list = new ArrayList<>();
        Logger logger = Logger.getLogger(this.getClass());
        try {
            list = orderService.findOrderDetailsByCode(code);
        }catch (Exception e){
            logger.info("没有订单详情数据");
        }
        List<String> list2 = new ArrayList<>();
        for (OrderDetails list1:list) {
            int count = list1.getCount();
            float price = list1.getFood().getPrice();
            float countprice = BaseCalculate.multiply(count,price);
            list2.add(String.valueOf(countprice));
        }
        request.setAttribute("OrderDetails",list);
        request.setAttribute("countprice",list2);
        request.getRequestDispatcher("admin/order/orderdetails.jsp").forward(request,response);
    }

    /*
    * 显示所有订单
    * */
    private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = new OrderServiceImpl();
        DeskService deskService = new DeskServiceImpl();
        List<Desk> desks = deskService.findAll();
        List<Orders> list = orderService.findAll();
        Collections.reverse(list);
        request.setAttribute("list",list);
        request.setAttribute("desks",desks);
        request.getRequestDispatcher("admin/order/orders.jsp").forward(request,response);
    }
}
