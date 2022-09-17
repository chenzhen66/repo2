package com.chenzhen.Servlet;

import com.chenzhen.entity.FoodType;
import com.chenzhen.service.FoodTypeService;
import com.chenzhen.service.impl.FoodTypeServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FoodTypeServlet", value = "/FoodTypeServlet")
public class FoodTypeServlet extends HttpServlet {

    FoodTypeService foodTypeService = new FoodTypeServiceImpl();

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
        }else if (action.equals("findByName")) {
            findByName(request,response);
        }else if (action.equals("addFoodType")) {
            addFoodType(request,response);
        }else if (action.equals("updateFoodTypeBefore")) {
            updateFoodTypeBefore(request,response);
        }else if (action.equals("updateFoodType")) {
            updateFoodType(request,response);
        }else if (action.equals("deleteFoodTypeById")) {
            deleteFoodTypeById(request,response);
        }
    }

    /*
    * 根据id删除餐品类型
    * */
    private void deleteFoodTypeById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int res = foodTypeService.deleteFoodTypeById(id);
        if (res >= 1) {
            request.setAttribute("msg","alert('删除成功');");
            request.setAttribute("local","FoodTypeServlet?action=findAll");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
        }else {
            request.setAttribute("msg","alert('删除失败了');");
            request.setAttribute("local","FoodTypeServlet?action=findAll");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
        }
    }

    /*
    * 修改餐品类型数据
    * */
    private void updateFoodType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tname = request.getParameter("tname");
        int id = Integer.parseInt(request.getParameter("id"));
        FoodType foodType = new FoodType();
        foodType.setId(id);
        foodType.setTname(tname);
        int res = foodTypeService.updatefoodtype(foodType);
        if (res >= 1) {
            request.setAttribute("msg","alert('修改成功');");
            request.setAttribute("local","FoodTypeServlet?action=findAll");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
        }else{
            request.setAttribute("msg","alert('修改失败');");
            request.setAttribute("local","admin/food/updatefoodtype,jsp");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
        }

    }

    /*
    * 修改之前显示信息
    * */
    private void updateFoodTypeBefore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<FoodType> list = foodTypeService.findById(id);
        request.setAttribute("list",list);
        request.getRequestDispatcher("admin/food/updatefoodtype.jsp").forward(request,response);
    }

    /*
    * 添加餐品类型
    * */
    private void addFoodType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tname = request.getParameter("tname");
        FoodType foodType = new FoodType();
        foodType.setTname(tname);
        int res = foodTypeService.addFoodType(foodType);
        if (res >= 1) {
            request.setAttribute("msg","alert('添加成功');");
            request.setAttribute("local","FoodTypeServlet?action=findAll");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
        }else {
            request.setAttribute("msg","alert('失败成功');");
            request.setAttribute("local","admin/food/addfoodtype.jsp");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
        }
    }

    /*
    * 根据名称查找
    * */
    private void findByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tname = request.getParameter("tname");
        List<FoodType> list = foodTypeService.findByName(tname);
        request.setAttribute("list",list);
        request.getRequestDispatcher("admin/food/foodtype.jsp").forward(request,response);
    }

    /*
    * 显示所有餐桌类型
    * */
    private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<FoodType> list = foodTypeService.findAll();
        request.setAttribute("list",list);
        request.getRequestDispatcher("admin/food/foodtype.jsp").forward(request,response);
    }
}
