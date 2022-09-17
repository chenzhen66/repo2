<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>商家登录</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="<c:url value='/webjars/jquery/3.6.0/jquery.min.js'/>"></script>
    <script src="<c:url value='/webjars/bootstrap/4.6.1/js/bootstrap.min.js'/>"></script>
    <link rel="stylesheet" href="<c:url value='/webjars/bootstrap/4.6.1/css/bootstrap.min.css'/> ">
    <link rel="stylesheet" href="<c:url value='/admin/css/login.css'/> ">
</head>

<body class="bodybg">

<div class="container">
    <div class="row " >
        <div class="col-sm-4"></div>
        <div class="col-sm-5" style="margin-top: 10%;background-color:rgba(255,255,255,0.8)">

            <form class="col-sm-12 mt-5 mb-5" action="<c:url value='/AdminServlet'/> " method="post">
                <input type="hidden" name="action" value="login">
                <ul class="list-group list-unstyled">
                    <li class="list-group-item-heading form-group"><h2 class="h2">后台登录</h2></li>
                </ul>
                <div class="form-group">
                    <label for="exampleInputUsername">账号</label>
                    <input maxlength="20" type="text" name="username" class="form-control" id="exampleInputUsername" placeholder="Username">
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">密码</label>
                    <input maxlength="20" type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                </div>
                <div class="form-group">
                    <input type="submit" class="form-control btn-primary" value="登录">
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