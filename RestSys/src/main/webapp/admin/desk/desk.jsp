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
</head>
<body>
<div class="container mt-3">
    <div class="row">
        <%--搜索框--%>
        <form class="form col-md-4" action="<c:url value='DeskServlet'/>" method="post">
            <input type="hidden" name="action" value="searchDesk">
            <div class="form-group ">
                <div class="input-group">
                    <input name="name" type="text" class="form-control" placeholder="请输入餐台名称">
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
        <form class="col-md-2" method="post" action="<c:url value='/DeskServlet'/> ">
            <input type="hidden" name="action" value="addDeskBefore">
            <input class="btn btn-primary" type="submit" value="添加">
        </form>
        <%--表格--%>
        <div class="col-md-12">
            <table class="table table-responsive-md table-striped text-center">
                <thead>
                <tr class="table-active table-dark">
                    <th scope="col">编号</th>
                    <th scope="col">餐台名称</th>
                    <th scope="col">餐台类型</th>
                    <th scope="col">使用情况</th>
                    <th scope="col">使用中顾客</th>
                    <th scope="col">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${list}">
                    <tr>
                        <td>
                                ${item.id}
                        </td>
                        <td>
                                ${item.name}
                        </td>
                        <td>
                                ${item.deskType.dp_name}
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${item.status==0}">
                                    空闲中
                                </c:when>
                                <c:otherwise>
                                    使用中
                                </c:otherwise>
                            </c:choose>

                        </td>
                        <td>
                             ${item.user.user_name}
                        </td>
                        <td>
                        <span>
                            <a class="btn btn-primary" href="<c:url value='DeskServlet?action=updateDeskBefore&id=${item.id}'/> ">修改</a>
                        </span>
                            /
                            <span>
                        <a href="javascript:if (confirm('确定要删除吗？')){window.location.href='<c:url value="DeskServlet?action=deletedeskById&id=${item.id}"/>'};" class="btn btn-primary">删除</a>
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
