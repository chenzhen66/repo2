<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>订单</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="<c:url value='/webjars/jquery/3.6.0/jquery.min.js'/>"></script>
    <script src="<c:url value='/webjars/bootstrap/4.6.1/js/bootstrap.min.js'/>"></script>
    <link rel="stylesheet" href="<c:url value='/webjars/bootstrap/4.6.1/css/bootstrap.min.css'/> ">
    <script>
        ${msg}
        function OrderInfo(code) {
            location.href="OrderServlet?action=findOrderdetilsByCode&code="+code+""
        }
        function shaixuanOrder(value){
            $("#value").val(value)
            $("#form").submit();
        }
        function Checkout(id) {
            $("#exampleModalCenter"+id).modal("show");

        }
        function fukuan(code) {
            location.href="OrderServlet?action=Checkout&code="+code+"&status="+2+""
        }
        function cancel(code) {
            location.href="OrderServlet?action=Checkout&code="+code+"&status="+3+""
        }
    </script>
</head>
<body>
<div class="container">
    <div class="row mt-4">
        <div class="col-md-12 mb-2">
            <form id="form" class="form-inline" action="<c:url value='/OrderServlet'/>">
                <input type="hidden" name="action" value="shaixuanOrder">
                <input id="value" type="hidden" name="value" value="">
            </form>
            <span>订单状态:</span>
            <button class="btn btn-info" onclick="shaixuanOrder(this.value)" value="0">已下单订单</button>
            <button class="btn btn-info" onclick="shaixuanOrder(this.value)" value="1">已出餐订单</button>
            <button class="btn btn-info" onclick="shaixuanOrder(this.value)" value="2">已完成订单</button>
            <span class="ml-3">下单时间:</span>
            <form class="form-inline " style="display: inline-block" action="<c:url value='/OrderServlet'/>">
                <input type="hidden" name="action" value="datesearch">
                <span>从</span><input class="form-control" id="tdate" name="tdate" type="datetime-local">
                <span>到</span><input class="form-control" id="edate" name="edate" type="datetime-local">
                <input class="btn btn-info" type="submit" value="搜索">
            </form>
        </div>
        <table class="table table-responsive-md table-borderless mb-5">
            <thead>
                <tr class="navbar-fixed-top bg-primary sticky-top text-white">
                    <th scope="col" class="ml-3">我的订单</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <c:forEach items="${orders}" var="item">
            <tbody class="border-bottom">
                <tr class="">
                    <th scope="row">
                        订单编号:${item.code}
                    </th>
                    <td></td>
                    <td>
                        下单时间:${item.data}
                    </td>
                </tr>
                <tr>
                    <th scope="row">桌号:${item.desk.name}</th>
                    <td></td>
                    <td>餐品数量:<span class="text-danger">${item.count}</span></td>
                    <td><span class="text-primary">餐品总价:${item.amount}</span></td>
                </tr>
                <tr>

                    <td>
                        订单状态:
                        <span class="text-info">
                            <c:choose>
                                <c:when test="${item.status==0}">
                                    菜品制作中
                                </c:when>
                                <c:when test="${item.status==1}">
                                    菜品已出餐
                                </c:when><c:when test="${item.status==2}">
                                    订单已完成
                                </c:when>
                                <c:otherwise>
                                    订单已取消
                                </c:otherwise>
                            </c:choose>
                        </span>

                    </td>
                    <td>订单备注:<span class="text-warning">${item.comments}</span></td>
                    <td>
                        <button class="btn btn-success" onclick="OrderInfo(this.value)" value="${item.code}">订单详情</button>
                    </td>

                    <td>

                        <c:choose>
                            <c:when test="${item.status==0||item.status==1}">
                                <button class="btn btn-warning mt-1" onclick="Checkout(this.value)" value="${item.id}" >结账</button>
                                <c:choose>
                                    <c:when test="${item.status==0}">
                                        <button class="btn btn-danger mt-1" onclick="cancel(this.value)" value="${item.code}">取消</button>
                                    </c:when>
                                </c:choose>
                            </c:when>
                        </c:choose>

                    </td>
                </tr>
            </tbody>
            <!-- 模态框 -->
            <div class="modal fade" id="exampleModalCenter${item.id}" tabindex="-1" data-backdrop="static" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalCenterTitle">付款</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body text-center">
                            您的订单金额为: <span class="text-danger">${item.amount}</span>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-dark" id="quxiao" data-dismiss="modal">取消</button>
                            <button type="button" class="btn btn-primary" onclick="fukuan(this.value)" value="${item.code}" id="fukuan" >付款</button>
                        </div>
                    </div>
                </div>
            </div>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
