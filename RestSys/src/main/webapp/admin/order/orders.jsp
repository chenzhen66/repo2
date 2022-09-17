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
        function OrderInfo(code) {
            location.href="AdminOrderServlet?action=findOrderdetilsByCode&code="+code+""
        }
        function cancel(code) {
            location.href="AdminOrderServlet?action=Checkout&code="+code+"&status="+3+""
        }
        function chucan(code) {
            location.href="AdminOrderServlet?action=Checkout&code="+code+"&status="+1+""
        }
    </script>
</head>
<body>
<div class="container-fluid">
    <div class="row mt-5">
        <%--搜索--%>
        <form class="container-fluid" action="<c:url value='/AdminOrderServlet'/>" method="post">
            <input type="hidden" name="action" value="search">
            <div class="form-inline">
                <span>查找：</span>
                <select class="form-control" name="deskid">
                    <c:forEach var="desk" items="${desks}">
                        <option value="${desk.id}">${desk.name}</option>
                    </c:forEach>
                </select>
                <lable>订单状态</lable>
                <select class="form-control" name="orderstatus">
                        <option selected value="ordering">订单进行中</option>
                        <option value="orderfinish">订单已完成</option>
                </select>
                <input class="btn btn-success ml-2" type="submit" value="查找">
            </div>
        </form>
            <%--主内容--%>
        <table class="table table-responsive-md table-striped mb-5">
            <thead class="text-center">
            <tr class="navbar-fixed-top bg-primary sticky-top text-white">
                <th scope="col">餐台</th>
                <th scope="col">下单人</th>
                <th scope="col">下单时间</th>
                <th scope="col">数量</th>
                <th scope="col">总价</th>
                <th scope="col">备注</th>
                <th scope="col">订单状态</th>
                <th scope="col">操作</th>
            </tr>
            </thead>
            <tbody class="text-center">
            <c:forEach items="${list}" var="item">
            <tr>
                <th scope="row">
                        ${item.desk.name}
                </th>
                <td>
                        ${item.user.user_name}
                </td>
                <td>${item.data}</td>
                <td>${item.count}</td>
                <td>${item.amount}</td>
                <td>${item.comments}</td>
                <td>
                    <c:choose>
                        <c:when test="${item.status==0}">
                            已下单待出餐
                        </c:when>
                        <c:when test="${item.status==1}">
                            餐品已出餐
                        </c:when>
                        <c:when test="${item.status==2}">
                            订单已完成
                        </c:when>
                        <c:otherwise>
                            订单已取消
                        </c:otherwise>
                    </c:choose>

                </td>
                <td>
                    <button class="btn btn-info" onclick="OrderInfo(this.value)" value="${item.code}">详情</button>
                    <c:choose>
                        <c:when test="${item.status<=1}">
                            <button class="btn btn-primary date-cell mt-1" onclick="chucan(this.value)" value="${item.code}">出餐</button>
                            <button class="btn btn-danger date-cell mt-1" onclick="cancel(this.value)" value="${item.code}">取消订单</button>
                        </c:when>

                    </c:choose>

                </td>
            </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
