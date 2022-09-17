package com.chenzhen.Servlet;

import com.chenzhen.entity.Food;
import com.chenzhen.entity.FoodType;
import com.chenzhen.service.FoodService;
import com.chenzhen.service.FoodTypeService;
import com.chenzhen.service.impl.FoodServiceImpl;
import com.chenzhen.service.impl.FoodTypeServiceImpl;
import com.chenzhen.utils.BaseCalculate;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FoodServlet", value = "/FoodServlet")
public class FoodServlet extends HttpServlet {

    FoodService foodService = new FoodServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html,charset=utf-8");
        String action = request.getParameter("action");
        /*
        * 根据不同action值执行不同的方法
        * */
        if (action.equals("findAll")) {
            findAll(request,response);
        }else if (action.equals("findByName")) {
            findByName(request,response);
        }else if (action.equals("addfoodBefore")) {
            addfoodBefore(request,response);
        }else if (action.equals("addFood")) {
            addFood(request,response);
        }else if (action.equals("updateFoodBefore")) {
            updateFoodBefore(request,response);
        }else if (action.equals("updateFood")) {
            updateFood(request,response);
        }else if (action.equals("deleteFoodById")) {
            deleteFoodById(request,response);
        }

    }

    /*
    * 获取餐品id
    * 根据餐品id删除餐品信息
    * */
    private void deleteFoodById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int res = foodService.deleteFoodById(id);
        if (res >= 1) {
            request.setAttribute("msg","alert('删除成功');");
            request.setAttribute("local","FoodServlet?action=findAll");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
        }else {
            request.setAttribute("msg","alert('删除失败了');");
            request.setAttribute("local","FoodServlet?action=findAll");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
        }
    }

    /*
    * 更新餐品信息
    * */
    private void updateFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int foodtype_id = Integer.parseInt(request.getParameter("foodtype_id"));
        float tpeice = Float.parseFloat(request.getParameter("tprice"));
        float eprice = Float.parseFloat("0."+request.getParameter("eprice"));
        float price= BaseCalculate.add(tpeice,eprice);
        String photo = request.getParameter("photo");
        Food food = new Food();
        food.setId(id);
        food.setName(name);
        food.setFoodtype_id(foodtype_id);
        food.setPrice(price);
        food.setImgpath(photo);
        int res = foodService.updateFood(food);
        if (res >= 1) {
            request.setAttribute("msg","alert('修改成功');");
            request.setAttribute("local","FoodServlet?action=findAll");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
        }else {
            request.setAttribute("msg","alert('修改失败了');");
            request.setAttribute("local","admin/food/updatefood.jsp");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
        }
    }

    /*
    * 更新餐品信息之前回显数据
    * */
    private void updateFoodBefore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        FoodTypeService foodTypeService = new FoodTypeServiceImpl();
        List<FoodType> list = foodTypeService.findAll();
        List<Food> list1 = foodService.findById(id);
        String price = String.valueOf(list1.get(0).getPrice());
        String[] split = price.split("\\.");
        String tprice = split[0];
        String eprice = split[1];
        request.setAttribute("tprice",tprice);
        request.setAttribute("eprice",eprice);
        request.setAttribute("list1",list1);
        request.setAttribute("list",list);
        request.getRequestDispatcher("admin/food/updatefood.jsp").forward(request,response);
    }

    /*
    * 添加餐品
    * */
    private void addFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int foodtype_id = Integer.parseInt(request.getParameter("foodtype_id"));
        float tpeice = Float.parseFloat(request.getParameter("tprice"));
        float eprice = Float.parseFloat("0."+request.getParameter("eprice"));
        float price= BaseCalculate.add(tpeice,eprice);
        String photo = request.getParameter("photo");
        Food food = new Food();
        food.setName(name);
        food.setFoodtype_id(foodtype_id);
        food.setPrice(price);
        food.setImgpath(photo);
        int res = foodService.addFood(food);
        if (res >= 1) {
            request.setAttribute("msg","alert('添加成功');");
            request.setAttribute("local","FoodServlet?action=findAll");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
        }else {
            request.setAttribute("msg","alert('添加失败了');");
            request.setAttribute("local","admin/food/addfood.jsp");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
        }
    }
    /*
    * 添加餐品信息时显示可选餐品类型
    * */
    private void addfoodBefore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FoodTypeService foodTypeService = new FoodTypeServiceImpl();
        List<FoodType> list=foodTypeService.findAll();
        request.setAttribute("list",list);
        request.getRequestDispatcher("admin/food/addfood.jsp").forward(request,response);
    }

    /*
    * 根据餐品名称模糊搜索餐品信息
    * */
    private void findByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        List<Food> list = foodService.findByName(name);
        request.setAttribute("list",list);
        request.getRequestDispatcher("admin/food/food.jsp").forward(request,response);
    }

    /*
    * 查询所有餐品信息
    * */
    private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Food> list = foodService.findAll();
        request.setAttribute("list",list);
        request.getRequestDispatcher("admin/food/food.jsp").forward(request,response);
    }
}
