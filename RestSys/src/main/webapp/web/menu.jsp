<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>欢迎使用黍味香点餐系统</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="<c:url value='/webjars/jquery/3.6.0/jquery.min.js'/>"></script>
    <script src="<c:url value='/webjars/bootstrap/4.6.1/js/bootstrap.min.js'/>"></script>
    <link rel="stylesheet" href="<c:url value='/webjars/bootstrap/4.6.1/css/bootstrap.min.css'/> ">
    <script>
        $(function () {
            $(".nav-item").click(function () {
               $(".nav-item").removeClass("active")
               $(this).addClass("active")
            })
        })
    </script>

</head>
<body>
<%--导航栏--%>
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <a class="navbar-brand ml-5" href="#">欢迎使用黍味香点餐系统</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <div class="col-sm-1"></div>
        <ul class="navbar-nav mr-auto font-weight-bold">
            <li class="nav-item active">
                <a class="nav-link" target="ifmain" href="<c:url value='/web/index.jsp'/> ">主页 <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" target="ifmain" href="<c:url value='/WebDeskServlet?action=findAllDesk'/> ">占座</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" target="ifmain" href="<c:url value='/ViewFoodServlet?action=findAll'/>" >点餐</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" target="ifmain" href="<c:url value='/CartServlet?action=findByUserId'/> ">购物车</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" target="ifmain" href="<c:url value='/OrderServlet?action=findAll'/> ">订单</a>
            </li>
            <li class="nav-item">
                <a class="nav-link bg-heise" target="ifmain" href="<c:url value='/web/view/mymsg.jsp'/> " >我的信息</a>
            </li>
        </ul>
        <c:choose >
            <c:when test="${user!=null}">
                <form class="form-inline my-2 my-lg-0" method="post" action="<c:url value='/UserServlet'/> ">
                    <input type="hidden" name="action" value="exit">
                    <span class="text-white">欢迎您！</span>
                    <span class="text-info">${user.getUser_name()}</span>
                    <c:choose>
                        <c:when test="${user.getSex()=='男'}">
                            <span class="text-info"> 先生</span>
                        </c:when>
                        <c:otherwise>
                            <span class="text-info"> 女士</span>
                        </c:otherwise>
                    </c:choose>
                    <button class="btn btn-outline-success ml-3" type="submit">退出</button>
                </form>
            </c:when>
            <c:otherwise>
                <form class="form-inline my-2 my-lg-0" method="post" action="<c:url value='/UserServlet'/>">
                    <input type="hidden" name="action" value="exit">
                    <span class="text-white">请先登录！</span>
                    <span class="text-info">${user.getUser_name()}</span>
                    <button class="btn btn-outline-success ml-3" type="submit">登陆</button>
                </form>
            </c:otherwise>
        </c:choose>

    </div>
</nav>

<main role="main" style="" >
        <iframe width="100%" height="100%" name="ifmain"  style="padding-top: 4%;" src="<c:url value='/web/index.jsp'/>" >
        </iframe>
</main>



</body>
</html>
