<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.88.1">
    <title>商家页面</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="<c:url value='/webjars/jquery/3.6.0/jquery.min.js'/>"></script>
    <script src="<c:url value='/webjars/jquery/3.6.0/jquery.slim.min.js'/>"></script>
    <script src="<c:url value='/webjars/bootstrap/4.6.1/js/bootstrap.min.js'/>"></script>
    <script src="<c:url value='/admin/js/dashboard.js'/>"></script>
    <link rel="stylesheet" href="<c:url value='/webjars/bootstrap/4.6.1/css/bootstrap.min.css'/> ">
    <link rel="stylesheet" href="<c:url value='/admin/css/dashboard.css'/> ">
    <link rel="stylesheet" href="<c:url value='/bootstrap-icons-1.8.1/bootstrap-icons.css'/> ">

    <script type="text/javascript">
        function iframeAutoFit(iframeObj){
            setTimeout(function(){if(!iframeObj) return;iframeObj.height=(iframeObj.Document?iframeObj.Document.body.scrollHeight:iframeObj.contentDocument.body.offsetHeight); console.log("test")},200)
        }
        function aaa() {
            iframeAutoFit(document.getElementById('iframe1'));
        };
        $(function () {
            $("a").click(function () {
                $("a").removeClass("active");
                $(this).addClass("active");
            })
        })

    </script>


    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>

</head>
<body>

<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" style="font-size: 1.75em">后台管理系统</a>
    <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-toggle="collapse" data-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
</nav>
<div class="container-fluid">
    <div class="row">
        <nav id="sidebarMenu" class="col-md-3 mt-3 col-lg-2 d-md-block bg-light sidebar collapse">
            <div class="sidebar-sticky">
                <ul class="nav flex-column" style="font-size: larger;">
                    <li class="nav-item border-bottom mt-2">
                        <a target="ifmain" class="nav-link active" href="<c:url value='/FoodServlet?action=findAll'/> ">
                            <i class="bi bi-bag-check-fill mr-2"></i>
                            菜品管理
                        </a>
                    </li>
                    <li class="nav-item border-bottom mt-2">
                        <a target="ifmain" class="nav-link" href="<c:url value='/FoodTypeServlet?action=findAll'/> ">
                            <i class="bi bi-egg-fill mr-2"></i>
                            菜品类型管理
                        </a>
                    </li>
                    <li class="nav-item border-bottom mt-2">
                        <a target="ifmain" class="nav-link" href="<c:url value='/DeskServlet?action=findAll'/> ">
                            <i class="bi bi-window-split mr-2"></i>
                            餐台管理
                        </a>
                    </li>
                    <li class="nav-item border-bottom mt-2">
                        <a target="ifmain" class="nav-link" href="<c:url value='/DeskTypeServlet?action=findAll'/> ">
                            <i class="bi bi-window-stack mr-2"></i>餐台类型管理
                        </a>
                    </li>
                    <li class="nav-item border-bottom mt-2">
                        <a target="ifmain"  class="nav-link" href="<c:url value='/AdminOrderServlet?action=findAll'/>">
                            <i class="bi bi-journal-text mr-2"></i>订单管理
                        </a>
                    </li>
                    <li class="nav-item border-bottom mt-2">
                        <a target="ifmain"  class="nav-link" href="<c:url value='/admin/updatepsd.jsp'/> ">
                            <i class="bi bi-person-fill mr-2"></i>修改密码
                        </a>
                    </li>
                </ul>

            </div>
        </nav>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4 h-100">
            <iframe id="iframe1" scrolling="no" frameborder="no" onload="aaa()" name="ifmain" height="100%" width="100%"  src="<c:url value='/FoodServlet?action=findAll'/>">

            </iframe>
        </main>
    </div>
</div>

</body>
</html>
