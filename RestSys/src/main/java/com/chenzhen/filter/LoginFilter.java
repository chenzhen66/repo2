package com.chenzhen.filter;
import com.chenzhen.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter")

public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        /*
        * 1.判断用户是否登录
        * 2.登录，继续
        * 3.未登录，转到登录页面
        * */

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        req.setCharacterEncoding("utf-8");
        User user = (User) session.getAttribute("user");
        if (user == null) {
            //没有登录
            req.setAttribute("msg","alert('请先登录!')");
            req.setAttribute("local","web/login.jsp");
            req.getRequestDispatcher("/web/pmsg.jsp").forward(request,response);
            return;
        }else {
            chain.doFilter(request, response);//继续执行
        }

    }
}
