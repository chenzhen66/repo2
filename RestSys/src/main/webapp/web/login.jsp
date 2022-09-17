<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户登录</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="<c:url value='/webjars/jquery/3.6.0/jquery.min.js'/>"></script>
    <script src="<c:url value='/webjars/bootstrap/4.6.1/js/bootstrap.min.js'/>"></script>
    <link rel="stylesheet" href="<c:url value='/webjars/bootstrap/4.6.1/css/bootstrap.min.css'/> ">
    <link rel="stylesheet" href="<c:url value='/web/css/login.css'/> ">
</head>

<body class="bodybg">

      <div class="container">
          <div class="row" >
              <div class="col-sm-5"></div>
              <div class="col-sm-5" style="margin-top: 10%;background-color:rgba(255,255,255,0.8);">

                  <form class="col-sm-12 mt-5 mb-5" action="<c:url value='/UserServlet'/> " method="post">
                      <input type="hidden" name="action" value="login">

                      <ul class="list-group list-unstyled">
                          <li class="list-group-item-heading form-group"><h2 class="h2">用户登录</h2></li>
                          <li class="list-group-item-heading form-group"><p class="lead">欢迎使用黍味香点餐系统</p></li>
                      </ul>
                      <div class="form-group">
                          <label for="exampleInputEmail1">邮箱</label>
                          <div class="form-group input-group">
                              <input name="mail" type="text" class="form-control" id="exampleInputEmail1" placeholder="EMail">
                              <div class="input-group-append" >
                                <span class="input-group-text" style="margin: 0;padding: 0">
                                    <select class="form-control"  name="mailend">
                                        <option selected value="@qq.com">@qq.com</option>
                                        <option value="@139.com">@139.com</option>
                                        <option value="@163.com">@163.com</option>
                                        <option value="@gmail.com">@gmail.com</option>
                                        <option value="@126.com">@126.com</option>
                                    </select>
                                </span>

                              </div>
                          </div>
                      </div>
                      <div class="form-group">
                          <label for="exampleInputPassword1">密码</label>
                          <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                      </div>
                      <div class="form-group">
                          <input type="submit" class="form-control  border-success" value="登录">
                      </div>
                      <div class="form-group text-center">
                          <a class="text-decoration-none" href="<c:url value='/web/register.jsp'/> ">立即注册</a>
                      </div>
                      <div class="text-center" >
                          <a class="text-decoration-none" style="color: red;font-size: 32px" href="<c:url value='/web/login.jsp'/> ">${msg}</a>
                      </div>

                  </form>
              </div>
          </div>

      </div>

</body>
</html>