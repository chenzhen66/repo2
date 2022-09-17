<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>占座</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="<c:url value='/webjars/jquery/3.6.0/jquery.min.js'/>"></script>
    <script src="<c:url value='/webjars/bootstrap/4.6.1/js/bootstrap.min.js'/>"></script>
    <link rel="stylesheet" href="<c:url value='/webjars/bootstrap/4.6.1/css/bootstrap.min.css'/> ">
    <script>

        ${msg}
        $(function (){
            $("#collapse${typelist.get(0).getDp_id()}").addClass("show")
        })
        function goorder(deskid) {
            location.href="OrderServlet?action=findByDeskid&deskid="+deskid+"";
        }
        function endDesk(deskid){
            location.href="WebDeskServlet?action=endDesk&deskid="+deskid+"";
        }
    </script>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="panel-group col-sm-12 mt-3" id="accordion">
            <c:forEach var="item" items="${typelist}">
                <div class="panel panel-default w-100">
                    <div class="panel-heading overflow-hidden">
                        <h4 class="panel-title bg-success" style="border-radius: 2%" >
                            <a class="btn col-sm-12 text-left text-white font-weight-bold" style="font-size: 1em;margin: 1%" data-toggle="collapse" data-parent="#accordion"
                               href="#collapse${item.dp_id}">
                                    ${item.dp_name}<span class="ml-5">适合${item.pnum}人以内就餐</span>
                            </a>
                        </h4>
                    </div>
                    <div id="collapse${item.dp_id}" class="panel-collapse collapse">
                        <div class="panel-body">
                            <div class="row">
                                <c:forEach var="list" items="${list}">
                                    <c:choose>
                                        <%--餐台表中与餐台类型id相同时--%>
                                        <c:when test="${item.dp_id==list.deskType.dp_id}">
                                            <div class="col-md-3">
                                                <h3 style="margin-left: 25%">${list.name}</h3>
                                                <img width="75%" src="<c:url value='/web/image/desk.jpeg'/> ">
                                                    <c:choose>
                                                        <c:when test="${list.status==0}">
                                                            <div style="z-index: 999;margin-top: -55%;margin-left: 22%">
                                                                <h4 >
                                                                    <span class="text-success">空闲中</span>
                                                                </h4>
                                                            </div>
                                                            <form class="form w-75" style="margin-top: 43%" method="post" action="<c:url value='/WebDeskServlet'/> ">
                                                                <input type="hidden" name="action" value="updatedeskuser">
                                                                <input type="hidden" name="deskid" value="${list.id}">
                                                                <input class="form-control btn btn-primary" name="changeDesk" type="submit" value="占座">
                                                            </form>
                                                        </c:when>
                                                        <c:otherwise>

                                                            <c:choose>

                                                                <c:when test="${user.getUser_id()==list.user.user_id}">
                                                                    <div style="z-index: 999;margin-top: -55%;margin-left: 22%">
                                                                        <h4 >
                                                                            <span class="text-danger" style="font-size: 16px">您正在使用中</span>
                                                                        </h4>
                                                                    </div>
                                                                    <div class=" w-75" style="margin-top: 45%">
                                                                        <div class="form-inline">
                                                                            <button class="btn btn-info mt-1 col-md-6" onclick="endDesk(this.value)" value="${list.id}">结束占位</button>
                                                                            <button class="btn btn-info mt-1 col-md-5 ml-1" onclick="goorder(this.value)" value="${list.id}" >去结账</button>
                                                                        </div>
                                                                    </div>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <div style="z-index: 999;margin-top: -55%;margin-left: 22%">
                                                                        <h4 >
                                                                            <span class="text-danger">使用中</span>
                                                                        </h4>
                                                                    </div>
                                                                    <form class="form w-75" style="margin-top: 43%" method="post" action="#">
                                                                        <input class="form-control btn btn-dark text-warning" name="changeDesk" type="button" value="顾客正在使用中">
                                                                    </form>
                                                                </c:otherwise>

                                                            </c:choose>
                                                        </c:otherwise>
                                                    </c:choose>

                                            </div>
                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>

    </div>
</div>



</body>
</html>
