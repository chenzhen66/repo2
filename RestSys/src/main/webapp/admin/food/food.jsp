<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>餐台类型</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="<c:url value='/webjars/jquery/3.6.0/jquery.min.js'/>"></script>
    <script src="<c:url value='/webjars/bootstrap/4.6.1/js/bootstrap.min.js'/>"></script>
    <link rel="stylesheet" href="<c:url value='/webjars/bootstrap/4.6.1/css/bootstrap.min.css'/> ">
    <script>
        /*页面加载时绑定事件*/
        $(function () {
          
        })

    </script>
</head>
<body>
<div class="container mt-3">
    <div class="row">
        <%--搜索框--%>
        <form class="form col-md-4" action="<c:url value='FoodServlet'/>" method="post">
            <input type="hidden" name="action" value="findByName">
            <div class="form-group ">
                <div class="input-group">
                    <input name="name" type="text" class="form-control" placeholder="请输入菜品名称">
                    <div class="col-md-1"></div>
                    <div class="input-group-append">
                        <span class="input-group" style="margin: 0;padding: 0">
                            <input class="form-control btn-primary" type="submit" value="搜索">
                        </span>
                    </div>
                </div>
            </div>
        </form>
        <%--添加--%>
        <form class="col-md-2" method="post" action="<c:url value='/FoodServlet'/> ">
            <input type="hidden" name="action" value="addfoodBefore">
            <input class="btn btn-primary" type="submit" value="添加">
        </form>
        <%--表格--%>
        <div class="col-md-12">
            <table class="table table-responsive-md table-striped text-center" >
                <thead>
                <tr class="table-active table-dark">
                    <th scope="col">图片</th>
                    <th scope="col">菜品名称</th>
                    <th scope="col">菜品类型</th>
                    <th scope="col">菜品价格</th>
                    <th scope="col">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${list}">
                    <tr>
                        <td>
                                <img alt="图片" width="80px", height="80px" src="<c:url value='${item.imgpath}'/> ">
                        </td>

                        <td>
                                ${item.name}
                        </td>

                        <td>
                                ${item.foodType.tname}
                        </td>

                        <td>
                                ${item.price}
                        </td>

                        <td>
                            <span>
                                <a class="btn btn-primary" href="<c:url value='FoodServlet?action=updateFoodBefore&id=${item.id}'/> ">修改</a>
                            </span>

                            <span class="ml-2">
                            <a href="javascript:if (confirm('确定要删除吗？')){window.location.href='<c:url value="FoodServlet?action=deleteFoodById&id=${item.id}"/>'};" class="btn btn-danger">删除</a>
                            </span>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>


</body>
</html>
