package com.chenzhen.Servlet;

import com.chenzhen.entity.Desk;
import com.chenzhen.entity.DeskType;
import com.chenzhen.service.DeskService;
import com.chenzhen.service.impl.DeskServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeskServlet", value = "/DeskServlet")
public class DeskServlet extends HttpServlet {

    DeskService deskService = new DeskServiceImpl();

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
        }else  if (action.equals("searchDesk")) {
            searchDesk(request,response);
        }else  if (action.equals("addDeskBefore")) {
            addDeskBefore(request,response);
        }else  if (action.equals("addDesk")) {
            addDesk(request,response);
        }else  if (action.equals("deletedeskById")) {
            deletedeskById(request,response);
        }else  if (action.equals("updateDeskBefore")) {
            updateDeskBefore(request,response);
        }else  if (action.equals("updateDesk")) {
            updateDesk(request,response);
        }
    }

    /*
    * 修改餐桌信息
    * */
    private void updateDesk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int desktype_id = Integer.parseInt(request.getParameter("desktype_id"));
        int id = Integer.parseInt(request.getParameter("id"));
        Desk desk = new Desk();
        desk.setId(id);
        desk.setName(name);
        desk.setDesktype_id(desktype_id);
        int res =deskService.updateDeskById(desk);
        if (res >= 1) {
            request.setAttribute("msg","alert('修改成功');");
            request.setAttribute("local","DeskServlet?action=findAll");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
        }else {
            request.setAttribute("msg","alert('修改失败');");
            request.setAttribute("local","admin/desk/updateDesk.jsp");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
        }
    }

    /*
    * 修改餐桌信息，回显
    * */
    private void updateDeskBefore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<Desk> list = deskService.findById(id);
        List<DeskType> list1 = deskService.finddesktype();
        request.setAttribute("list",list);
        request.setAttribute("list1",list1);
        request.getRequestDispatcher("admin/desk/updateDesk.jsp").forward(request,response);
    }

    /*
    * 删除座位
    * */
    private void deletedeskById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int res = deskService.deleteDeskById(id);
        Logger logger = Logger.getLogger(this.getClass());
        if (res >= 1) {
            request.getRequestDispatcher("DeskServlet?action=findAll").forward(request,response);
        }else{
            logger.info("删除失败");
        }
    }

    /*
    * 添加座位
    * */
    private void addDesk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int desktype_id = Integer.parseInt(request.getParameter("desktype_id"));
        Desk desk = new Desk();
        desk.setDesktype_id(desktype_id);
        desk.setName(name);
        int res = deskService.insertDesk(desk);
        if (res >= 1) {
            request.setAttribute("msg","alert('添加成功');");
            request.setAttribute("local","DeskServlet?action=findAll");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
        }else {
            request.setAttribute("msg","alert('添加失败');");
            request.setAttribute("local","admin/desk/addDesk.jsp");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
        }

    }

    /*
    * 添加座位时显示座位类型
    * */
    private void addDeskBefore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<DeskType> list =deskService.finddesktype();
        request.setAttribute("list",list);
        request.getRequestDispatcher("admin/desk/addDesk.jsp").forward(request,response);
    }

    /*
    * 根据名字搜索座位
    * */
    private void searchDesk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        List<Desk> list = deskService.searchDesk(name);
        request.setAttribute("list",list);
        request.getRequestDispatcher("admin/desk/desk.jsp").forward(request,response);

    }

    /*
    * 查询所有座位信息
    * */
    private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DeskService deskService = new DeskServiceImpl();
        List<Desk> list = deskService.findAll();
        request.setAttribute("list",list);
        request.getRequestDispatcher("admin/desk/desk.jsp").forward(request,response);
    }
}
