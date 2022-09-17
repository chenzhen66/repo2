package com.chenzhen.Servlet.Web;

import com.chenzhen.entity.Code;
import com.chenzhen.entity.User;
import com.chenzhen.service.CodeService;
import com.chenzhen.service.impl.CodeServiceImpl;
import com.chenzhen.utils.CodeEmailUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@WebServlet(name = "CodeServlet", value = "/CodeServlet")
public class CodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html,charset=utf-8");
        String action = request.getParameter("action");
        if (action.equals("addCode")) {
            addCode(request,response);
        }

    }

    /*
    * 添加验证码数据
    * */
    private void addCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/text,charset=utf-8");
        User user = (User) request.getSession().getAttribute("user");
        Logger logger = Logger.getLogger(this.getClass());
        int user_id=0;
        try {
            user_id=user.getUser_id();
        }catch (Exception e){
            logger.info("没有登录");
        }
        int random = (int)((Math.random()*9+1)*100000);//生成六位随机数
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1 = simpleDateFormat.format(date);
        CodeService codeService = new CodeServiceImpl();
        Code code = new Code();
        code.setCode(String.valueOf(random));
        code.setUser_id(user_id);
        code.setDate(date1);
        int result = codeService.addCode(code);
        if (result >= 0) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(response.getWriter(),true);
            CodeEmailUtils codeEmailUtils = new CodeEmailUtils();
            try {
                codeEmailUtils.sendCodeMail(user.getMail(),user.getUser_name(), String.valueOf(random));
            }catch (Exception e){
                System.out.println("没有网络!");
            }
        }else {
            ObjectMapper objectMapper = new ObjectMapper();
            String msg = "获取验证码失败";//获取验证码失败信息
            objectMapper.writeValue(response.getWriter(),msg);
        }

    }
}
