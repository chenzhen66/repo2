package com.chenzhen.Servlet.Web;

import com.chenzhen.entity.Code;
import com.chenzhen.entity.User;
import com.chenzhen.service.CodeService;
import com.chenzhen.service.UserService;
import com.chenzhen.service.impl.CodeServiceImpl;
import com.chenzhen.service.impl.UserServiceImpl;
import com.chenzhen.utils.EmailUtils;
import com.chenzhen.utils.MD5Utils;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@WebServlet(name = "UserServlet", value = "/UserServlet")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html,charset=utf-8");
        String action = request.getParameter("action");

        if (action.equals("login")) {
            login(request,response);
        }else if ("register".equals(action)) {
            register(request,response);
        }else if ("exit".equals(action)) {
            exit(request,response);
        }else if ("rmenu".equals(action)) {
            rmenu(request,response);
        }else if ("updateMsg".equals(action)) {
            updateMsg(request,response);
        }else if ("updatepsd".equals(action)) {
            updatepsd(request,response);
        }

    }

    /*
    * 修改密码
    * */
    private void updatepsd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newpassword1 = request.getParameter("newpassword1").trim();
        String newpassword = request.getParameter("newpassword").trim();
        String code = request.getParameter("code").trim();
        User user = (User) request.getSession().getAttribute("user");
        if (newpassword.equals("") || newpassword1.equals("")) {
            request.setAttribute("alert","alert('密码不能为空！');");
            request.setAttribute("modalid","exampleModalCenter");
            request.getRequestDispatcher("web/view/mymsg.jsp").forward(request,response);
            return;
        }
        int user_id = 0;
        try {
            user_id=user.getUser_id();
        }catch (Exception e){
            System.out.println("没有登录");
        }
        if (code == null||code.equals("")) {
            request.setAttribute("msg","alert('验证码不能为空！');");
            request.setAttribute("local","web/view/mymsg.jsp");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
            return;
        }
        if (!newpassword.equals(newpassword1)) {
            request.setAttribute("msg","alert('密码输入不一致！');");
            request.setAttribute("local","web/view/mymsg.jsp");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
            return;
        }
        CodeService codeService = new CodeServiceImpl();
        UserService userService = new UserServiceImpl();
        Code code1  =new Code();
        code1.setCode(code);
        code1.setUser_id(user_id);
        List<Code> list = codeService.findcodeByuseridandcode(code1);
        if (list.size()!=0) {
            String date =list.get(0).getDate();
            Date date2 = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowdate = simpleDateFormat.format(date2);
            try {
                int miao = (int) getTime(date,nowdate);
                if (miao <=90) {
                    String psd = MD5Utils.MD5(newpassword1);
                    user.setPassword(psd);
                    userService.update(user);
                    request.setAttribute("msg","alert('修改成功,请重新登录！');");
                    request.setAttribute("local","web/login.jsp");
                    request.getRequestDispatcher("web/pmsg.jsp").forward(request,response);
                }else {
                    request.setAttribute("msg","alert('验证码已过期！');");
                    request.setAttribute("local","web/view/mymsg.jsp");
                    request.getRequestDispatcher("web/msg.jsp").forward(request,response);
                    return;
                }
            } catch (ParseException e) {
                System.out.println("获取秒数失败!");
            }
        }else {
            request.setAttribute("msg","alert('验证码输入错误！');");
            request.setAttribute("local","web/view/mymsg.jsp");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
            return;
        }
    }

    //计算两个时间相差的秒数
    public static long getTime(String startTime, String endTime) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long eTime = df.parse(endTime).getTime();
        long sTime = df.parse(startTime).getTime();
        long diff = (eTime - sTime) / 1000;
        return diff;
    }

    //更新个人信息
    private void updateMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        User user1 = (User) request.getSession().getAttribute("user");
        Logger logger = Logger.getLogger(this.getClass());
        int user_id=0;
        try {
            user_id=user1.getUser_id();
        }catch (Exception e){
            logger.info("没有登录");
        }
        if (name == null||name.equals("")) {
            request.setAttribute("msg","alert('邮箱不能为空！');");
            request.setAttribute("local","web/view/mymsg.jsp");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
            return;
        }
        User user = new User();
        user.setUser_name(name);
        user.setSex(sex);
        user.setUser_id(user_id);
        UserService userService = new UserServiceImpl();
        int result = userService.updatamsg(user);
        if (result >= 1) {
            request.setAttribute("msg","alert('修改成功！');");
            request.setAttribute("local","web/view/mymsg.jsp");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
            user1.setUser_name(name);
            user1.setSex(sex);
            request.getSession().setAttribute("user",user1);
            return;
        }

    }

    /*
    *
    * */
    private void rmenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        * 跳转主页面
        * */
        request.getRequestDispatcher("web/menu.jsp").forward(request,response);
    }


    /*
    * 退出
    * */
    private void exit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*
         * 1.将Session失效
         * 2.转向ProductServlet中,action=findIndex
         * */
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("web/login.jsp");
    }

    /*
    * 注册
    * */
    private void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String tmail = request.getParameter("mail");
        String mail = request.getParameter("mail")+request.getParameter("mailend");
        String password = request.getParameter("password");
        UserService userService = new UserServiceImpl();
        if (tmail == null|tmail.trim().equals("")) {
            Logger logger = Logger.getLogger(UserServlet.class);
            logger.debug("邮箱不能为空！");
            request.setAttribute("msg","alert('邮箱不能为空！');");
            request.setAttribute("local","web/register.jsp");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
            return;
        }
        if (password == null||password.trim().equals("")) {
            request.setAttribute("msg","alert('密码不能为空!')");
            request.setAttribute("local","web/login.jsp");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
            return;
        }
        List<User> list = userService.findByMail(mail);
        if (list.size()!=0) {
            request.setAttribute("msg","alert('账号已存在!')");
            request.setAttribute("local","web/login.jsp");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
            return;
        }
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        password = MD5Utils.MD5(password);
        User user = new User();
        user.setMail(mail);
        user.setPassword(password);
        user.setUser_name(name);
        user.setSex(sex);

        int user_id =userService.insert(user);
        if (user_id != 1) {
            request.setAttribute("msg","alert('注册失败！');");
            request.setAttribute("local","web/register.jsp");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
        }else {
            Logger logger = Logger.getLogger(UserServlet.class);
            logger.debug("注册成功！");
            EmailUtils send = new EmailUtils();
            try {
                send.sendActivateMail(mail,name);
            }catch (Exception e){
                System.out.println("没有网络");
            }
            request.setAttribute("msg","alert('注册成功！');");
            request.setAttribute("local","web/login.jsp");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
        }


    }

    /*
    * 1.获取登录信息
    * 2.调用userService的login方法，并判断sql是否执行成功
    * 3.返回1，则执行成功，进入主页面
    * */
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Logger logger = Logger.getLogger("登录！");
        String tmail = request.getParameter("mail");
        String mail = request.getParameter("mail")+request.getParameter("mailend");
        if (tmail == null||tmail.trim().equals("")) {
            request.setAttribute("msg","alert('邮箱不能为空!')");
            request.setAttribute("local","web/login.jsp");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
            return;
        }
        String password = request.getParameter("password");
        if (password == null||password.trim().equals("")) {
            request.setAttribute("msg","alert('密码不能为空!')");
            request.setAttribute("local","web/login.jsp");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
            return;
        }
        password = MD5Utils.MD5(password);
        User user = new User();
        user.setMail(mail);
        user.setPassword(password);
        UserService userService = new UserServiceImpl();
        User user1 = new User();
        user1 = userService.login(user);
        if (user1 == null) {
            logger.info("账号或密码错误！");
            request.setAttribute("msg","alert('账号或密码错误!')");
            request.setAttribute("local","web/login.jsp");
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
        }else {
            request.setAttribute("local","UserServlet?action=rmenu");
            request.getSession().setAttribute("user",user1);
            request.getRequestDispatcher("web/msg.jsp").forward(request,response);
        }

    }
}
