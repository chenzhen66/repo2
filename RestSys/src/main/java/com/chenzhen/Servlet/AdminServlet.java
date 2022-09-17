package com.chenzhen.Servlet;

import com.chenzhen.entity.Admin;
import com.chenzhen.service.AdminService;
import com.chenzhen.service.impl.AdminServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminServlet", value = "/AdminServlet")
public class AdminServlet extends HttpServlet {

    AdminService adminService = new AdminServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html,charset=utf-8");
        String action = request.getParameter("action");
        if (action.equals("login")){
            login(request,response);
        }else if (action.equals("updatepsd")) {
            updatepsd(request,response);
        }

    }

    /*
    * 修改管理员密码
    * */
    private void updatepsd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String opsd = request.getParameter("opsd");
        String opsd2 = request.getParameter("opsd2");
        String npsd = request.getParameter("npsd");
        Logger logger = Logger.getLogger(AdminServlet.class);
        Admin admin = new Admin();
        admin.setAdmin_id(1);
        admin.setPassword(npsd);
        Admin admin1=null;
        try {
            admin1 = (Admin) request.getSession().getAttribute("adminmsg");
        }catch (Exception e){
            System.out.println("");
        }

        if (admin1 == null) {
            request.setAttribute("msg","alert('请先登录!')");
            request.setAttribute("local","admin/updatepsd.jsp");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
            return;
        }
        String password = admin1.getPassword();
        if (opsd.equals(opsd2)) {

            if (password.trim().equals(opsd.trim())) {
                int res = adminService.updatepsd(admin);
                if (res >= 1) {
                    request.setAttribute("msg","alert('密码修改成功，请重新登录!')");
                    request.setAttribute("local","admin/login.jsp");
                    request.getRequestDispatcher("web/msg.jsp").forward(request,response);
                }else {
                    logger.info("修改异常！");
                    request.setAttribute("msg","修改失败了");
                    request.getRequestDispatcher("admin/updatepsd.jsp").forward(request,response);
                }

            }else {
                request.setAttribute("msg","alert('旧密码错误!')");
                request.setAttribute("local","admin/updatepsd.jsp");
                request.getRequestDispatcher("web/msg.jsp").forward(request,response);
                return;
            }

        }else {
            logger.info("两次密码输入不一致！");
            request.setAttribute("msg","两次密码输入不一致");
            request.getRequestDispatcher("admin/updatepsd.jsp").forward(request,response);
        }

    }

    /*
    * 登录
    * */
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Logger logger = Logger.getLogger(AdminServlet.class);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Admin admin = new Admin();
        admin.setAdmin_id(1);
        admin.setAdmin_num(username);
        admin.setPassword(password);
        List<Admin> adminList = adminService.findbyuser(admin);
        if (adminList.size()==0) {
            logger.info("账号或密码错误！");
            request.setAttribute("msg","alert('账号或密码错误!')");
            request.setAttribute("local","admin/login.jsp");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
        }else {
            request.getSession().setAttribute("adminmsg",admin);
            request.setAttribute("local","admin/menu.jsp");
            request.getSession().setAttribute("mail",username);
            logger.info("登录成功!");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
        }
    }
}
