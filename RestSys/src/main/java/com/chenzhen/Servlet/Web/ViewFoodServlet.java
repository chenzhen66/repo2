package com.chenzhen.Servlet.Web;

import com.chenzhen.entity.Cart;
import com.chenzhen.entity.Food;
import com.chenzhen.entity.FoodType;
import com.chenzhen.entity.User;
import com.chenzhen.service.CartService;
import com.chenzhen.service.FoodService;
import com.chenzhen.service.FoodTypeService;
import com.chenzhen.service.impl.CartServiceImpl;
import com.chenzhen.service.impl.FoodServiceImpl;
import com.chenzhen.service.impl.FoodTypeServiceImpl;
import com.chenzhen.utils.BaseCalculate;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "ViewFoodServlet", value = "/ViewFoodServlet")
public class ViewFoodServlet extends HttpServlet {
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
        }else if (action.equals("search")) {
            search(request,response);
        } 

    }

    /*
    * 根据餐品类型或者餐品名称搜索餐品信息
    * */
    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FoodService foodService = new FoodServiceImpl();
        FoodTypeService foodTypeService = new FoodTypeServiceImpl();
        String value = request.getParameter("value");
        String type = request.getParameter("type");
        /*
        * 如果选中的是餐品类型
        * 先根据餐品类型模糊查询出类型信息（用于显示餐单分类）
        * 遍历餐品中该类型的餐品信息
        * 转发到餐品页面
        * */
        if (type.equals("typename")) {
            if (value.equals("")) {
                List<Food> foods = foodService.findAll();
                List<FoodType> foodTypes = foodTypeService.findAll();
                request.setAttribute("food",foods);
                request.setAttribute("foodtype",foodTypes);
                request.getRequestDispatcher("/web/view/food.jsp").forward(request,response);
                return;
            }
            try {
                List<FoodType> foodTypes = foodTypeService.findByName(value);
                if (foodTypes == null||foodTypes.size()==0) {
                    request.setAttribute("foodtype",null);
                    request.getRequestDispatcher("/web/view/food.jsp").forward(request,response);
                }else {
                    //搜索结果有多个类型
                    List<Food> foods = new ArrayList<>();
                    for (FoodType foodType : foodTypes) {
                        //没有数据，get出异常
                        int typeid = foodType.getId();
                        List<Food> food = foodService.findByTypeId(typeid);
                        //合并集合
                        foods.addAll(food);
                    }
                    
                    //查不到出异常显示所有菜品
                    // foods.addAll(food);
                    request.setAttribute("food",foods);
                    request.setAttribute("foodtype",foodTypes);
                    request.getRequestDispatcher("/web/view/food.jsp").forward(request,response);
                }

            }catch (Exception e){
                request.setAttribute("foodtype",null);
                request.getRequestDispatcher("/web/view/food.jsp").forward(request,response);
            }
            /*
            * 传入根据餐品名称
            * 先查询出模糊查询出餐品信息
            * 再根据餐品信息查询出这些餐品都输入什么餐品类型
            * */
        }else {
            if (value.equals("")) {
                List<Food> foods = foodService.findAll();
                List<FoodType> foodTypes = foodTypeService.findAll();
                request.setAttribute("food",foods);
                request.setAttribute("foodtype",foodTypes);
                request.getRequestDispatcher("/web/view/food.jsp").forward(request,response);
                return;
            }
            try {
                List<Food> foods = foodService.findByName(value);
                if (foods == null||foods.size()==0) {
                    request.setAttribute("foodtype",null);
                    request.getRequestDispatcher("/web/view/food.jsp").forward(request,response);
                    return;
                }
                List<FoodType> foodtypes = new ArrayList<>();
                //餐桌id集合
                List<Integer> typeids = new ArrayList<>();
                for (Food food : foods) {
                    typeids.add(food.getFoodType().getId());
                }
                //去除list中重复项
                List<Integer> listWithoutDuplicates = typeids.stream().distinct().collect(Collectors.toList());
                foodtypes.addAll(foodTypeService.findById(listWithoutDuplicates));
                request.setAttribute("food",foods);
                request.setAttribute("foodtype",foodtypes);
                request.getRequestDispatcher("/web/view/food.jsp").forward(request,response);
            }catch (Exception e){
                request.setAttribute("foodtype",null);
                request.getRequestDispatcher("/web/view/food.jsp").forward(request,response);
            }
        }
    }

    /*
    * 显示所有菜单中餐品
    * */
    private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FoodService foodService = new FoodServiceImpl();
        FoodTypeService foodTypeService = new FoodTypeServiceImpl();
        List<Food> list  = foodService.findAll();
        List<FoodType> list1 = foodTypeService.findAll();

        CartService cartService = new CartServiceImpl();
        User user= (User) request.getSession().getAttribute("user");
        int user_id = user.getUser_id();
        List<Cart> list2 = cartService.findByUserId(user_id);
        float pricecount = 0.0F;
        int countNumber = 0;
        for (Cart list3:list2){
            float d = list3.getFood().getPrice();
            int c  = list3.getCount();
            countNumber+=c;
            float money = BaseCalculate.multiply(d,c);//金额相乘
            pricecount+=money;
        }
        request.setAttribute("countNum",countNumber);
        request.setAttribute("money",pricecount);
        request.setAttribute("food",list);
        request.setAttribute("foodtype",list1);
        request.getRequestDispatcher("web/view/food.jsp").forward(request,response);
    }
}
