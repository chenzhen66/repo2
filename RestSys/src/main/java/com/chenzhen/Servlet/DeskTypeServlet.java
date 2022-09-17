package com.chenzhen.Servlet;

import com.chenzhen.entity.DeskType;
import com.chenzhen.service.DeskTypeService;
import com.chenzhen.service.impl.DeskTypeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeskTypeServlet", value = "/DeskTypeServlet")
public class DeskTypeServlet extends HttpServlet {
    DeskTypeService deskTypeService = new DeskTypeServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html,charset=utf-8");
        String action = request.getParameter("action");
        if (action.equals("findAll")) {
            findAll(request,response);
        }else  if (action.equals("updatePer")) {
            updatePer(request,response);
        }else  if (action.equals("deleteDeskTypeById")) {
            deleteDeskTypeById(request,response);
        }else  if (action.equals("addDeskType")) {
            addDeskType(request,response);
        }else  if (action.equals("searchDT")) {
            searchDT(request,response);
        }else  if (action.equals("updateDeskTypeBefore")) {
            updateDeskTypeBefore(request,response);
        }else  if (action.equals("updateDeskType")) {
            updateDeskType(request,response);
        }
    }

    /*
    * 修改座位类型信息
    * */
    private void updateDeskType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int pnum = Integer.parseInt(request.getParameter("pnum"));
        DeskType deskType = new DeskType();
        deskType.setDp_id(id);
        deskType.setDp_name(name);
        deskType.setPnum(pnum);
        int res = deskTypeService.updateDeskType(deskType);
        if (res >= 1) {
            request.setAttribute("msg","alert('修改成功');");
            request.setAttribute("local","DeskTypeServlet?action=findAll");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
        }else {
            request.setAttribute("msg","alert('修改失败');");
            request.setAttribute("local","admin/desk/updateDeskType.jsp");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
        }

    }

    /*
    * 修改信息之前显示原有的信息
    * */
    private void updateDeskTypeBefore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<DeskType> list = deskTypeService.findById(id);
        request.setAttribute("list",list);
        request.getRequestDispatcher("admin/desk/updateDeskType.jsp").forward(request,response);
    }

    /*
    * 根据名称搜索
    * */
    private void searchDT(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dp_name = request.getParameter("dp_name");
        List<DeskType> list = deskTypeService.findByname(dp_name);
        request.setAttribute("list",list);
        request.getRequestDispatcher("admin/desk/desktype.jsp").forward(request,response);
    }

    /*
    * 添加餐桌类型
    * */
    private void addDeskType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int pnum=0;
        try {
            pnum = Integer.parseInt(request.getParameter("pnum"));
        }catch (Exception e){
            System.out.println("数据转换异常");
        }
        DeskType deskType = new DeskType();
        deskType.setPnum(pnum);
        deskType.setDp_name(name);
        int res = deskTypeService.insert(deskType);
        if (res >= 1) {
            request.setAttribute("msg","alert('添加成功');");
            request.setAttribute("local","DeskTypeServlet?action=findAll");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
        }else {
            request.setAttribute("msg","alert('添加失败');");
            request.setAttribute("local","admin/desk/addDeskType.jsp");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
        }
    }

    /*
    * 删除餐桌类型
    * */
    private void deleteDeskTypeById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int res = deskTypeService.deleteDeskTypeById(id);
        Logger logger = Logger.getLogger(this.getClass());
        if (res >= 1) {
            request.getRequestDispatcher("DeskTypeServlet?action=findAll").forward(request,response);
        }else{
            logger.info("删除失败");
        }
    }


    /*
    * 修改类型建议可容纳人数
    * */
    private void updatePer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        int Pnum = Integer.parseInt(request.getParameter("count"));
        int id = Integer.parseInt(request.getParameter("id"));
        DeskType deskType = new DeskType();
        deskType.setPnum(Pnum);
        deskType.setDp_id(id);
        int res = deskTypeService.updatePer(deskType);
        Logger logger = Logger.getLogger(this.getClass());
        if (res >= 1) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getWriter(),Pnum);
        }else{

            logger.info("修改数量失败");
        }
    }

    /*
    * 显示所有餐桌类型
    * */
    private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<DeskType> list = deskTypeService.findAll();
        request.setAttribute("list",list);
        request.getRequestDispatcher("admin/desk/desktype.jsp").forward(request,response);
    }
}
