package com.chenzhen.filter;

import com.chenzhen.entity.Desk;
import com.chenzhen.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "DeskFilter")
public class DeskFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        /*
         * 1.判断用户是否占座
         * 2.占座了，继续
         * 3.未占座，转到占座页面
         * */

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        req.setCharacterEncoding("utf-8");
        Desk desk = (Desk) session.getAttribute("desk");
        if (desk == null) {
            //没有占座
            req.setAttribute("msg","alert('请先占座!')");
            req.setAttribute("local","WebDeskServlet?action=findAllDesk");
            req.getRequestDispatcher("/web/msg.jsp").forward(request,response);
            return;
        }else {
            chain.doFilter(request, response);//继续执行
        }
    }
}
