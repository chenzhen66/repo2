package com.chenzhen.Servlet.Web;

import com.chenzhen.entity.Desk;
import com.chenzhen.entity.DeskType;
import com.chenzhen.entity.Orders;
import com.chenzhen.entity.User;
import com.chenzhen.service.DeskService;
import com.chenzhen.service.DeskTypeService;
import com.chenzhen.service.OrderService;
import com.chenzhen.service.impl.DeskServiceImpl;
import com.chenzhen.service.impl.DeskTypeServiceImpl;
import com.chenzhen.service.impl.OrderServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "WebDeskServlet", value = "/WebDeskServlet")
public class WebDeskServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html,charset=utf-8");
        String action = request.getParameter("action");
        if (action.equals("findAllDesk")) {
            findAllDesk(request,response);
        }else if (action.equals("updatedeskuser")) {
            updatedeskuser(request,response);
        }else if (action.equals("desksession")) {
            desksession(request,response);
        }else if (action.equals("endDesk")) {
            endDesk(request,response);
        }

    }

    /*
    *取消占座
    * */
    private void endDesk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int deskid = Integer.parseInt(request.getParameter("deskid"));
        Logger logger = Logger.getLogger(this.getClass());
        int user_id = 0;
        try {
            User user = (User) request.getSession().getAttribute("user");
            user_id = user.getUser_id();
        }catch (Exception e){
            logger.info("没有登录");
        }
        Desk desk = new Desk();
        desk.setId(deskid);
        desk.setStatus(0);
        DeskService deskService = new DeskServiceImpl();
        OrderService orderService = new OrderServiceImpl();
        List<Orders> list = orderService.findByDeskidAndUserid(deskid,user_id);
        if (list.size()!=0) {
            request.setAttribute("msg","alert('取消失败，您还有未完成的订单')");
            request.setAttribute("local","WebDeskServlet?action=findAllDesk");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
            return;
        }else {
            int result = deskService.updatestatus(desk);
            if (result >= 1) {
                logger.info("取消占座");
                request.getSession().setAttribute("desk",null);
                request.setAttribute("msg","alert('已取消占座')");
                request.setAttribute("local","WebDeskServlet?action=findAllDesk");
                request.getRequestDispatcher("web/msg.jsp").forward(request,response);
            }else {
                request.setAttribute("msg","alert('取消占座失败')");
                request.setAttribute("local","WebDeskServlet?action=findAllDesk");
                request.getRequestDispatcher("web/msg.jsp").forward(request,response);
            }
        }


    }

    /*
    * 已经占座的话存入session
    * */
    private void desksession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DeskService deskService = new DeskServiceImpl();
        Logger logger = Logger.getLogger(this.getClass());
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
        request.getRequestDispatcher("/web/index.jsp").forward(request,response);
    }


    /*
    * 占座
    * */
    private void updatedeskuser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Desk desk1 = new Desk();
        Logger logger = Logger.getLogger(this.getClass());
        try {
            desk1 = (Desk) request.getSession().getAttribute("desk");
        }catch (Exception e){
            logger.info("没有占座");
        }
        if (desk1 != null) {
            request.setAttribute("msg","alert('您已经占过座位了');");
            request.setAttribute("local","WebDeskServlet?action=findAllDesk");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
            return;
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int deskid = Integer.parseInt(request.getParameter("deskid"));
        Desk desk = new Desk();
        desk.setId(deskid);
        desk.setStatus(1);
        desk.setUser_id(user.getUser_id());
        DeskService deskService = new DeskServiceImpl();
        int res = deskService.updatestatus(desk);
        if (res >= 1) {
            request.setAttribute("msg","alert('占座成功')");
            request.getSession().setAttribute("desk",desk);
            request.getRequestDispatcher("/WebDeskServlet?action=findAllDesk").forward(request,response);
        }else {
            request.setAttribute("msg","alert('占座失败，请稍后再试')");
            request.getRequestDispatcher("/WebDeskServlet?action=findAllDesk").forward(request,response);
        }
    }

    /*
    * 查询所有座位
    * */
    private void findAllDesk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DeskTypeService deskTypeService = new DeskTypeServiceImpl();
        DeskService deskService = new DeskServiceImpl();
        List<DeskType> typeList = deskTypeService.findAll();
        List<Desk> list = deskService.findAll();
        Logger logger = Logger.getLogger(this.getClass());
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
        Collections.reverse(typeList);
        request.setAttribute("typelist",typeList);
        request.setAttribute("list",list);
        request.getRequestDispatcher("web/view/desk.jsp").forward(request,response);
    }
}
