<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>标题</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="<c:url value='/webjars/jquery/3.6.0/jquery.min.js'/>"></script>
    <script src="<c:url value='/webjars/bootstrap/4.6.1/js/bootstrap.min.js'/>"></script>
    <link rel="stylesheet" href="<c:url value='/webjars/bootstrap/4.6.1/css/bootstrap.min.css'/> ">
    <script>
        ${msg}
    </script>
</head>
<body>
<div class="container">
    <div class="row mt-5">
        <table class="table table-responsive-md table-cell mb-5">
            <thead class="text-center">
            <tr class="navbar-fixed-top bg-primary sticky-top text-white">
                <th scope="col">餐品图片</th>
                <th scope="col">餐品名称</th>
                <th scope="col">单价</th>
                <th scope="col">购买数量</th>
                <th scope="col">商品总价</th>
                <th scope="col">状态</th>
                <th scope="col">操作</th>

            </tr>
            </thead>
            <tbody class="text-center">
            <c:forEach items="${OrderDetails}" var="item">
                <tr>
                    <th scope="row">
                        <img width="100px" height="80px" src="<c:url value='${item.food.imgpath}'/> " alt="图片">
                    </th>
                    <td>${item.food.name}</td>
                    <td>${item.food.price}</td>
                    <td>${item.count}</td>
                    <td>${countprice.get(OrderDetails.indexOf(item))}</td>
                    <td>
                        <c:choose>
                            <c:when test="${item.status==0}">
                                制作中
                            </c:when>
                            <c:when test="${item.status==0}">
                                待上桌
                            </c:when>
                            <c:otherwise>
                                已完成
                            </c:otherwise>

                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${item.status==0}">
                                <a class="btn btn-danger" href="<c:url value='/OrderServlet?action=cancelOrderFood&id=${item.id}&code=${item.orders.code}&price=${item.food.price}&count=${item.count}'/> ">取消</a>
                            </c:when>
                            <c:otherwise>
                                不可操作
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
