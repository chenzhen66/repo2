<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户注册</title>
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

    <div class="row " >
        <div class="col-sm-4"></div>
        <div class="col-sm-5" style="margin-top: 10%;background-color: rgba(255,255,255,0.8)">
            <form class="form col-md-12  mt-5 mb-5" action="<c:url value='/UserServlet'/> " method="post">
                <input type="hidden" name="action" value="register">
                <ul class="list-group list-unstyled">
                    <li class="list-group-item-heading form-group"><h2 class="h2">用户注册</h2></li>
                    <li class="list-group-item-heading form-group"><p class="lead">欢迎使用黍味香点餐系统</p></li>
                </ul>

                <div class="form-group">
                    <label>邮箱</label>
                    <div class="input-group">
                        <input maxlength="20" name="mail" type="text" class="form-control" placeholder="EMail">
                        <div class="input-group-append">
                        <span class="input-group" style="margin: 0;padding: 0">
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
                    <label>密码</label>
                    <input maxlength="20" name="password" class="form-control" placeholder="Password" type="password">
                </div>
                <div class="form-group">
                    <label>姓名</label>
                    <input maxlength="20" name="name" class="form-control" placeholder="your name" type="text">
                </div>
                <div class="form-group">
                    <label>性别</label>
                    <select class="form-control" name="sex">
                           <option selected value="男">男</option>
                           <option value="女">女</option>
                    </select>
                </div>
                <div class="form-group">
                    <button class="btn btn-primary w-100" type="submit">注册</button>
                </div>
                <div class="text-center">
                    <a class="text-decoration-none" href="<c:url value='/web/login.jsp'/> ">立即登录</a>
                </div>
                <div class="text-center" >
                    <a class="text-decoration-none" style="color: red;font-size: 32px" href="<c:url value='/web/login.jsp'/> ">${msg}</a>
                </div>
            </form>
        </div>
    </div>

</div>

</body>
<script>
    ${msg}
</script>
</html>