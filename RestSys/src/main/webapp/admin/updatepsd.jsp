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
        function validatepsd() {
            var opsd = $("#opsd").value()
            var opsd2 = $("#opsd2").value()
            alert("ceshi")
            if (opsd.trim().eq(opsd2.trim())){
                $("#msg").text("√密码驶入一致")
            }
        }
    </script>
</head>
<body>
<div class="container mt-5">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-4 border" style="padding: 5%">
            <form action="<c:url value='/AdminServlet'/> " method="post">
                <input type="hidden" name="action" value="updatepsd">
                <ul class="list-group list-unstyled">
                    <li class="list-group-item-heading form-group"><h2 class="h2">修改密码</h2></li>
                </ul>
                <div class="form-group">
                    <lable>旧密码</lable>
                    <input id="opsd" name="opsd" class="form-control" type="password">
                </div>
                <div class="form-group">
                    <lable>再次输入旧密码</lable>
                    <input id="opsd2" name="opsd2"  class="form-control" type="password" onblur="validatepsd()">
                </div>
                <div class="form-group">
                    <lable>新密码</lable>
                    <input id="npsd" name="npsd"  class="form-control"  type="password">
                    <span id="msg"></span>
                </div>
                <div class="form-group">
                    <input type="submit"  class="btn btn-primary w-100" value="修改">
                </div>
                <div class="form-group">
                    <lable id="msg" class="text-danger">${msg}</lable>
                </div>
            </form>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>
</body>
</html>
