<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>后台管理系统</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="<c:url value='/webjars/jquery/3.6.0/jquery.min.js'/>"></script>
    <script src="<c:url value='/webjars/bootstrap/4.6.1/js/bootstrap.min.js'/>"></script>
    <link rel="stylesheet" href="<c:url value='/webjars/bootstrap/4.6.1/css/bootstrap.min.css'/> ">
    <style>
        .nonav{
            width: 0px !important;
        }
        .ce_nav{
            position: relative;
            height: 500px;
            background: #e6dfdf;
            width: 200px;
            float: left;
            transition:width 1s;
            -webkit-transition:width 1s;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function () {
            var isClosed=0;
            $("button").click(function(){
                $(".ce_nav").addClass('nonav');
                $(".flex-column").addClass('d-none');
                if (isClosed==0) {
                    $(".ce_nav").addClass('nonav');
                    $(".flex-column").addClass('d-none');
                    isClosed=1;
                }else{
                    $(".ce_nav").removeClass('nonav');
                    $(".flex-column").removeClass('d-none');
                    isClosed=0;
                }
            })
        })
    </script>
</head>
<body>
<div class="container-fluid h-100">
    <div class="row h-100">
        <%--头部导航栏--%>
        <nav class="fixed-top" style="height: 60px;background-color: #333; text-align: center" >
            <h2 style="color: white; padding-top: 10px">餐厅后台系统</h2>
        </nav>
        <%--左侧导航栏--%>
        <div class="col-md-2 col-sm-2 ce_nav h-100" style="background-color: #3c3c3c;padding-top: 3%;">
            <button type="button" class="btn" style="color: white;z-index:9999;position:fixed;top: 3%;border: white 1px solid">
                三
            </button>
            <ul class="col-md-12 nav flex-column text-center mt-5 font-menu-button" style="background-color: #333">
                <li class="nav-item mt-1">
                    <a class="btn text-white w-100" style="background-color: #23272b" data-toggle="collapse" href="#collapse1" role="button" aria-expanded="false" aria-controls="collapseExample">
                        菜品管理
                    </a>
                    <ul class="nav collapse show flex-column text-left pl-5 font-menu-button" id="collapse1" >
                        <li class="nav-item w-100" >
                            <a target="ifmain" class="nav-link" href="<c:url value='/FoodServlet?action=findAll'/> ">菜品管理</a>
                        </li>
                        <li class="nav-item w-100">
                            <a target="ifmain" class="nav-link" href="<c:url value='/FoodTypeServlet?action=findAll'/> ">菜品类型管理</a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item mt-1">
                    <a class="btn text-white w-100" style="background-color: #23272b" data-toggle="collapse" href="#collapse2" role="button" aria-expanded="false" aria-controls="collapseExample">
                        餐台管理
                    </a>
                    <ul class="nav collapse show flex-column text-left pl-5 font-menu-button" id="collapse2" style="">
                        <li class="nav-item w-100" >
                            <a target="ifmain" class="nav-link" href="<c:url value='/DeskServlet?action=findAll'/> ">餐台管理</a>
                        </li>
                        <li class="nav-item w-100">
                            <a target="ifmain" class="nav-link" href="<c:url value='/DeskTypeServlet?action=findAll'/> ">餐台类型管理</a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item mt-1">
                    <a target="ifmain" style="background-color: #23272b" class="nav-link" href="<c:url value='/AdminOrderServlet?action=findAll'/>">订单管理</a>
                </li>
                <li class="nav-item mt-1">
                    <a target="ifmain" style="background-color: #23272b" class="nav-link" href="<c:url value='/admin/updatepsd.jsp'/> ">修改密码</a>
                </li>
            </ul>

        </div>
        <div class="col-md-10 col-sm-10 h-100" style="padding-top: 5% ;height: 100%;background: #575657;">
            <iframe name="ifmain" height="100%" width="100%"  src="<c:url value='/FoodServlet?action=findAll'/>">

            </iframe>
        </div>
    </div>
</div>



</body>
</html>
