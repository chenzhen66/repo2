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

    function addCart(value) {

        let Str = value.split(",");
        let user_id = ${user.getUser_id()};
        let food_id = Str[0];
        let desk_id = Str[1];
        $.post(
            "${pageContext.request.contextPath}/CartServlet",
            {"action":"addCart","user_id":user_id,"food_id":food_id,"desk_id":desk_id},
            function (data) {
                var money =  parseFloat(data[0]).toFixed(1)
                $(".countNum").text(" "+data[1]+" ")
                $("#money").text(" "+ money+" " )
            },"json"
         )
    }
    $("${exampleModalCenter}").modal("show");
    $("#exampleModalCenter").modal("show");
    ${msg}
    $(function (){
        $("#collapse${foodtype.get(0).getId()}").addClass("show");
    })

    </script>
</head>
<body>
<div class="container ">
    <div class="row">

        <!-- 模态框 -->
        <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalCenterTitle">提示信息</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        已经占过座了
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="fixed-top col-md-11 bg-dark text-white  mt-1 pl-5" style="margin-left: 3%">
            <form action="<c:url value='ViewFoodServlet'/> " method="post">
                <input type="hidden" name="action" value="search">
                <div class="form-inline col-md-6 pl-5 mt-2">
                    <label>按:</label>
                    <select name="type" class="form-control border-success ml-2">
                        <option  value="name">
                            菜品名称
                        </option>
                        <option selected value="typename">
                            菜品类型
                        </option>
                    </select>
                    <input name="value" class="form-control border-success ml-2" type="text">
                    <input class="form-control border-success ml-2" type="submit" value="搜索">
                </div>
            </form>
        </div>
        <%--主内容--%>
        <div class="panel-group col-sm-12" style="margin-top: 7%;margin-bottom: 6%" id="accordion">
            <c:choose>
                <c:when test="${foodtype==null||foodtype.size()==0}">
                    <h1 class="h1 text-danger">没有查询到结果</h1>
                    <a class="col-sm-3 btn btn-primary" href="javascript: history.back(-1);">返回</a>
                </c:when>
            </c:choose>
            <c:forEach var="item" items="${foodtype}">
                <div class="panel panel-default w-100">
                    <div class="panel-heading overflow-hidden">
                        <h4 class="panel-title bg-warning" style="border-radius: 2%" >
                            <a class="btn col-sm-12 text-left text-white font-weight-bold" style="font-size: 1em;margin: 1%" data-toggle="collapse" data-parent="#accordion"
                               href="#collapse${item.id}">
                                    ${item.tname}
                            </a>
                        </h4>
                    </div>
                    <div id="collapse${item.id}" class="panel-collapse collapse">
                        <div class="panel-body">
                            <div class="row">
                                <c:forEach var="list" items="${food}">
                                    <c:choose>
                                        <c:when test="${item.id==list.foodType.id}">
                                            <div class="col-md-3">
                                                <h3 style="margin-left: 25%">${list.name}</h3>
                                                <img width="100%" height="200px" src="<c:url value='${list.imgpath}'/> ">
                                                <h5 class="mt-2 ml-2">价格：${list.price}</h5>
                                                <button onclick="addCart(this.value)" class="button btn-primary form-control mt-3 mb-3"  value="${list.id},${item.id}">加入购物车</button>
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>

            <footer class="container-fluid fixed-bottom bg-white mb-1" >
                <div class="row w-75 text-white m-auto bg-dark" style="height: 60px">
                    <div class="col-md-4" style="margin: auto">
                        <div class="m-auto text-white col-sm-1 bg-danger" style="z-index: 999;position: absolute;top: 1px;border-radius: 50%;">
                            <div class="countNum" style="margin-left:-10px">${countNum}</div>
                        </div>
                        <img width="60px" height="60px" src="<c:url value='/web/image/cart.png'/> ">
                        共计:<span class="text-warning countNum" > ${countNum} </span>件
                    </div>
                    <div class="col-md-4 m-auto">
                        <div>
                            共计金额:<span class="text-warning" id="money"> ${money} </span>元
                        </div>

                    </div>
                    <div class="col-md-4 text-right" style="margin: auto">
                        <a class="col-md-12 btn btn-primary" href="<c:url value='/CartServlet?action=findByUserId'/> ">去购物车</a>
                    </div>
                </div>
            </footer>

        </div>
    </div>
</div>



</body>
</html>
