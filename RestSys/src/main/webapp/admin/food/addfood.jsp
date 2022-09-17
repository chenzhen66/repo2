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
    <script type="text/javascript">

        function preview(obj){
            var img = document.getElementById("previewimg");
            img.src = window.URL.createObjectURL(obj.files[0]);
        }

    </script>
</head>
<body>
<div class="container mt-5">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6 border" style="padding: 5%">
            <form action="<c:url value='/ImageUploadServlet'/> " method="post" enctype="multipart/form-data">
                <input type="hidden" name="action" value="addFood">
                <ul class="list-group list-unstyled">
                    <li class="list-group-item-heading form-group"><h2 class="h2">添加菜品</h2></li>
                </ul>
                <div class="form-group">
                    <lable>菜品名称</lable>
                    <input name="name" class="form-control" type="text">
                </div>
                <div class="form-group">
                    <lable>菜品类型</lable>
                    <select class="form-control" name="foodtype_id">
                        <c:forEach var="item" items="${list}">
                            <option value="${item.id}">${item.tname}</option>
                        </c:forEach>
                    </select>

                </div>
                <div class="form-group">
                    <div class="container-fluid" style="margin: 0;padding: 0">
                        <div class="row">
                            <lable class="col-md-12">菜品价格:</lable>
                            <input name="tprice" class="form-control col-md-8" style="margin-left: 5%" value="0" type="text" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" >
                            <span class="text-right">.</span>
                            <input name="eprice" class="form-control col-md-2" style="margin-left: 5%" value="0" type="text" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" >
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <lable>菜品图片</lable>
                    <input id="imgpath" name="imgpath" type="file" class="form-control" style="width:200px;" onChange="preview(this)" />
                </div>
                <div class="form-group">
                    <img id="previewimg" width="200px" height="200px" src="/" id="img1">
                </div>
                <div class="form-group">
                    <input type="submit"  class="form-control btn btn-primary" value="添加">
                </div>
            </form>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>


</body>

</html>
