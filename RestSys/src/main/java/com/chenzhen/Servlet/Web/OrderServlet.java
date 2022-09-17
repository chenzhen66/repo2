package com.chenzhen.Servlet.Web;

import com.chenzhen.entity.OrderDetails;
import com.chenzhen.entity.Orders;
import com.chenzhen.entity.User;
import com.chenzhen.service.OrderService;
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

@WebServlet(name = "OrderServlet", value = "/OrderServlet")
public class OrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("html/text,charaset=utf-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action").trim();
        if (action.equals("findAll")) {
            findAll(request,response);//用户所有订单
        }else if (action.equals("findOrderdetilsByCode")) {
            findOrderdetilsByCode(request,response);//订单详情
        }else if (action.equals("shaixuanOrder")) {
            shaixuanOrder(request,response);//根据订单状态查找
        }else if (action.equals("Checkout")) {
            Checkout(request,response);//结账和取消
        }else if (action.equals("datesearch")) {
            datesearch(request,response);//按照下单日期搜索
        }else if (action.equals("findByDeskid")) {
            findByDeskid(request,response);//根据餐桌id查找
        }else if (action.equals("cancelOrderFood")) {
            cancelOrderFood(request,response);//取消单个餐品
        }
    }

    /*
    * 取消订单中单个餐品
    * */
    private void cancelOrderFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String code = request.getParameter("code");
        String price = request.getParameter("price");
        int count = Integer.parseInt(request.getParameter("count"));
        Orders orders = new Orders();
        orders.setCode(code);
        orders.setAmount(Float.parseFloat(price));
        orders.setCount(count);
        OrderService orderService = new OrderServiceImpl();
        Logger logger = Logger.getLogger(this.getClass());

        int result = orderService.deleteOrderDetailsById(id);
        if (result >= 1) {
            List<OrderDetails> list = orderService.findOrderDetailsByCode(code);
            if (list.size()<1) {
                orderService.deleteOrderBycode(code);
                logger.info("删除执行了！");
            }
            orderService.UpdeteOrder(orders);
            request.setAttribute("msg","alert('已成功取消！')");
            findOrderdetilsByCode(code,request,response);
        }else {
            request.setAttribute("msg","alert('取消失败了！请联系服务员取消!')");
            findOrderdetilsByCode(code,request,response);
        }

    }

    /*
    * 根据桌号查询订单
    * */
    private void findByDeskid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int deskid = Integer.parseInt(request.getParameter("deskid"));
        Logger logger = Logger.getLogger(this.getClass());
        int user_id = 0;
        try {
            User user = (User) request.getSession().getAttribute("user");
            user_id = user.getUser_id();
        }catch (Exception e){
            logger.info("没有登录");
        }
        OrderService orderService = new OrderServiceImpl();
        List<Orders> list = orderService.findByDeskidAndUserid(deskid,user_id);
        if (list.size()!=0) {
            request.setAttribute("orders",list);
            request.getRequestDispatcher("web/view/orders.jsp").forward(request,response);
        }else {
            request.setAttribute("msg","alert('请还没有下单！')");
            request.setAttribute("local","WebDeskServlet?action=findAllDesk");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
        }
    }

    /*
    * 根据日期查询订单
    * */
    private void datesearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tdate = request.getParameter("tdate");
        String edate = request.getParameter("edate");
        Logger logger = Logger.getLogger(this.getClass());
        int user_id = 0;
        User user = (User) request.getSession().getAttribute("user");
        try {
            user_id = user.getUser_id();
        }catch (Exception e){
            logger.info("没有登录");
        }
        OrderService orderService = new OrderServiceImpl();
        //compareTo返回 0 表示时间日期相同
        //compareTo返回 1 表示日期1>日期2
        //compareTo返回 -1 表示日期1
        if (edate.compareTo(tdate)==-1) {
            logger.info("时间反了");
            request.setAttribute("msg","alert('结束日期大于开始日期！')");
            request.setAttribute("local","OrderServlet?action=findAll");
            request.getRequestDispatcher("/web/msg.jsp").forward(request,response);
            return;
        }
        List<Orders> list = orderService.FindByDate(tdate,edate,user_id);
        logger.info(tdate);
        logger.info(edate);
        logger.info(user_id);
        request.setAttribute("orders",list);
        request.getRequestDispatcher("web/view/orders.jsp").forward(request,response);
    }

    //结账，更新订单状态
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
                if (status.equals("2")) {
                    request.setAttribute("msg","alert('付款成功!')");
                }else {
                    request.setAttribute("msg","alert('订单已取消!')");
                }

            }else {
                request.setAttribute("msg","alert('失败!')");
            }
        }else {
            request.setAttribute("msg","alert('失败!')");
        }
        request.setAttribute("local","OrderServlet?action=findAll");
        request.getRequestDispatcher("web/msg.jsp").forward(request,response);
    }

    //根据订单状态筛选订单
    private void shaixuanOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int user_id = 0;
        Logger logger = Logger.getLogger(this.getClass());
        try {
            User user = (User) request.getSession().getAttribute("user");
            user_id=user.getUser_id();
        }catch (Exception e){
            logger.info("没有登录");
        }
        int status = Integer.parseInt(request.getParameter("value"));
        OrderService orderService = new OrderServiceImpl();
        List<Orders> list1 = new ArrayList<>();
        if (status == 2) {
            list1 = orderService.findByStatusAndUserId(user_id,3);
        }
        List<Orders> list = orderService.findByStatusAndUserId(user_id,status);
        list.addAll(list1);
        Collections.reverse(list);
        request.setAttribute("orders",list);
        request.getRequestDispatcher("web/view/orders.jsp").forward(request,response);
    }

    /*
    * 根据订单号显示订单详情,重载方法
    * */
    private void findOrderdetilsByCode(String code,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        request.getRequestDispatcher("web/view/orderdetails.jsp").forward(request,response);
    }
    /*
    *根据订单号显示订单详情
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
            float price = 0.0f;
            try {
                price = list1.getFood().getPrice();
            }catch (Exception e){
                request.setAttribute("OrderDetails",null);
                request.setAttribute("countprice",null);
                request.getRequestDispatcher("web/view/orderdetails.jsp").forward(request,response);
                return;
            }
            float countprice = BaseCalculate.multiply(count,price);
            list2.add(String.valueOf(countprice));
        }
        request.setAttribute("OrderDetails",list);
        request.setAttribute("countprice",list2);
        request.getRequestDispatcher("web/view/orderdetails.jsp").forward(request,response);
    }

    /*
    * 查询用户的所有订单信息到页面
    * */
    private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        int userId = user.getUser_id();
        OrderService orderService = new OrderServiceImpl();
        List<Orders> list = orderService.FindAllByUserId(userId);
        Collections.reverse(list);
        request.setAttribute("orders",list);
        request.getRequestDispatcher("web/view/orders.jsp").forward(request,response);

    }
}
