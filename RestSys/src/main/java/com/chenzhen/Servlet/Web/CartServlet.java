package com.chenzhen.Servlet.Web;

import com.chenzhen.entity.*;
import com.chenzhen.service.CartService;
import com.chenzhen.service.DeskService;
import com.chenzhen.service.OrderService;
import com.chenzhen.service.impl.CartServiceImpl;
import com.chenzhen.service.impl.DeskServiceImpl;
import com.chenzhen.service.impl.OrderServiceImpl;
import com.chenzhen.utils.BaseCalculate;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@WebServlet(name = "CartServlet", value = "/CartServlet")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html,charaset=utf-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if (action.equals("addCart")) {
            addCart(request,response);
        }else if (action.equals("findByUserId")) {
            findByUserId(request,response);
        }else if (action.equals("updateCartCount")) {
            updateCartCount(request,response);
        }else if (action.equals("deleteone")) {
            deleteone(request,response);
        }else if (action.equals("DeleteMore")) {
            DeleteMore(request,response);
        }else if (action.equals("BuyOrder")) {
            BuyOrder(request,response);
        }
    }

    /*
    * 下单，添加orders和ordersDetils
    * */
    private void BuyOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] cartIds = request.getParameterValues("text");
        String comments = request.getParameter("comments").trim();
        Desk desk = (Desk) request.getSession().getAttribute("desk");
        org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CartServlet.class);
        int desk_id = 0;
        try {
            desk_id = desk.getId();
        }
        catch (Exception e){
            System.out.println("没有占座");
        }
        String code = UUID.randomUUID().toString().replace("-","");
        User user = (User) request.getSession().getAttribute("user");
        int userId = user.getUser_id();
        int status =0 ;
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowdate = simpleDateFormat.format(date);
        if (comments.equals("")) {
            comments="无";
        }
        int orderdetailsResult = 0;
        OrderService orderService = new OrderServiceImpl();
        int orderResult = 0;
        float amount = 0.0f;
        int countNum = 0;
        List<String> list = new ArrayList<>();
        CartService cartService = new CartServiceImpl();
        for (String cartId:cartIds){
            int cartid = Integer.parseInt(cartId.split(",")[0]);//购物车id
            List<Cart> carts = cartService.findById(cartid);
            System.out.println(carts);
            list.add(String.valueOf(cartid));
            int count = carts.get(0).getCount();//数量
            countNum+=count;
            float price = carts.get(0).getFood().getPrice();//价格
            price = BaseCalculate.multiply(price,count);
            amount=BaseCalculate.add(amount,price);
            int foodid = carts.get(0).getFood().getId();
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setOrder_id(code);
            orderDetails.setCount(count);
            orderDetails.setFood_id(foodid);
            orderDetails.setUser_id(userId);
            orderDetails.setStatus(0);
            orderdetailsResult += orderService.addOrderDetails(orderDetails);
        }
        int deleteResult = cartService.deleteById(list);
        Orders orders = new Orders();
        orders.setCode(code);
        orders.setData(nowdate);
        orders.setDesk_id(desk_id);
        orders.setAmount(amount);
        orders.setCount(countNum);
        orders.setUser_id(userId);
        orders.setComments(comments);
        orders.setStatus(0);
        logger.info(orders);
        orderResult = orderService.AddOrder(orders);
        if (orderResult != 0&&orderdetailsResult!=0&&deleteResult!=0) {
            request.setAttribute("msg","alert('下单成功!')");
            request.getRequestDispatcher("OrderServlet?action=findAll").forward(request,response);
        }else {
            request.setAttribute("msg","alert('下单失败')");
            request.setAttribute("local","OrderServlet?action-findAll");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
        }

    }

    /*
    * 删除多个菜品
    * */
    private void DeleteMore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] cartIds = request.getParameterValues("text");
        CartService cartService = new CartServiceImpl();
        //System.out.println(Arrays.toString(cartIds));
        List<String> list = new ArrayList<>();
        try{
            for (String cartId:cartIds){
                String[] c = cartId.split(",");
                list.add(c[0]);
            }
        }catch (Exception e){
            Logger logger = Logger.getLogger("失败信息");
            logger.info("没有选中");
        }
        int result = cartService.deleteById(list);
        if (result >= 1) {
            request.setAttribute("modalid","#exampleModalCenter");
            request.setAttribute("msg","删除成功");
            request.getRequestDispatcher("/CartServlet?action=findByUserId").forward(request,response);
        }

    }

    /*
    * 删除一个菜品
    * */
    private void deleteone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cartid = Integer.parseInt(request.getParameter("cartid"));
        List<String> list = new ArrayList<>();
        list.add(String.valueOf(cartid));
        CartService cartService = new CartServiceImpl();
        int result = cartService.deleteById(list);
        if (result >= 0) {
            request.setAttribute("modalid","#exampleModalCenter");
            request.setAttribute("msg","删除成功");
            request.getRequestDispatcher("/CartServlet?action=findByUserId").forward(request,response);
        }
    }

    /*
    * 修改购买的餐品数量
    * */
    private void updateCartCount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int foodid = Integer.parseInt(request.getParameter("foodid"));
        int count = Integer.parseInt(request.getParameter("count"));
        User user = (User) request.getSession().getAttribute("user");
        int userid = user.getUser_id();
        CartService cartService = new CartServiceImpl();
        Cart cart = new Cart();
        cart.setUser_id(userid);
        cart.setFood_id(foodid);
        cart.setCount(count);
        cartService.updateCountByUserAndFoodId(cart);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getWriter(),count);
    }

    /*
    * 根据用户id显示用户的购物车
    * */
    private void findByUserId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DeskService deskService = new DeskServiceImpl();
        org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());
        try {
            User user = (User) request.getSession().getAttribute("user");
            int user_id = user.getUser_id();
            List<Desk> desks = deskService.findByUserid(user_id);
            if (desks.size()!=0) {
                request.getSession().setAttribute("desk",desks.get(0));
            }
        }catch (Exception e){
            logger.info("没有登录");
        }

        User user = (User) request.getSession().getAttribute("user");
        int userId = user.getUser_id();
        CartService cartService = new CartServiceImpl();
        List<Cart> list = cartService.findByUserId(userId);
        float pricecount = 0.0F;
        int count = 0;
        for (Cart list1:list){
            float d = list1.getFood().getPrice();
            int num = list1.getCount();
            count+=num;
            pricecount+=d;
        }
        request.setAttribute("countmoney",pricecount);
        request.setAttribute("countNumber",count);
        request.setAttribute("list",list);
        request.getRequestDispatcher("/web/view/cart.jsp").forward(request,response);
    }

    /*
    * 添加餐品到购物车
    * */
    private void addCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        int food_id = Integer.parseInt(request.getParameter("food_id"));
        int desk_id = Integer.parseInt(request.getParameter("desk_id"));
        CartService cartService = new CartServiceImpl();
        Cart cart = new Cart();
        cart.setUser_id(user_id);
        cart.setDesk_id(desk_id);
        cart.setFood_id(food_id);
        cart.setCount(1);
        //获取表中要添加的菜品
        List<Cart> foodCart = cartService.findByUserAndFoodId(cart);
        //要添加商品的总数
        int count = 0;
        for (Cart foodCart1:foodCart){
            count+=foodCart1.getCount();
        }

        //判断要加购的餐品是否已经存在，没有的话添加一条记录，有则更新
        if (foodCart.size()==0) {
            cartService.insert(cart);
        }else {
            count+=1;
            cart.setCount(count);
            cartService.updateCountByUserAndFoodId(cart);
        }
        List<Cart> list = cartService.findByUserId(user_id);

        float pricecount = 0.0F;
        int countNumber = 0;
        for (Cart list1:list){
           float d = list1.getFood().getPrice();
           int c  = list1.getCount();
           countNumber+=c;
           float money = BaseCalculate.multiply(d,c);//金额相乘
           pricecount+=money;
        }
        List<String> list2 = new ArrayList<>();
        list2.add(String.valueOf(pricecount));
        list2.add(String.valueOf(countNumber));
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),list2);

    }
}
