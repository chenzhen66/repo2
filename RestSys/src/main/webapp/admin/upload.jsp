<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>上传文件</title>
    <script src="<c:url value='/webjars/jquery/3.6.0/jquery.min.js'/>"></script>
    <script src="<c:url value='/webjars/bootstrap/4.6.1/js/bootstrap.min.js'/>"></script>
    <link rel="stylesheet" href="<c:url value='/webjars/bootstrap/4.6.1/css/bootstrap.min.css'/> ">

    <script>

        function preview(obj){
            var img = document.getElementById("previewimg");
            img.src = window.URL.createObjectURL(obj.files[0]);
        }

        $(function (){
            $("#sure").click(function (){
                var path = $("#path").val();
                $("#filepath",window.opener.document).val(path);
                $("#img1",window.opener.document).attr("src",<c:url value='/'/> +path+  "?t="+Math.random());
                window.close();
            });
        });

        /*//上传前判断文件啊是否选择，且必须为图片
        function validateName(){
            var name = $("input[name = 'imgpath']");
            var result = $("#result");
            result.innerHTML="";
            if (name.val() == null || $.trim(name.val()) === ""){
                result.html("上传文件名称不能为空!");
                return false;
            }

            var ext = ["jpg","gif","bmp","png","JPG","GIF","BMP","PNG","jpeg","JPEG"];
            var start = name.val().lastIndexOf(".")+1
            var type = name.val().substr(start);//后缀
            var flag = false;
            for(var i=0;i<ext.length;i++){
                if (type == ext[i]){
                    flag = true;
                    break;
                }
            }
            if (flag){
                return true;
            }else {
                result.html("请选择一个有效的图片文件");
                return false;
            }
        }*/
    </script>
</head>

<body>



<div class="container">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-4 border" style="padding: 5%">
            <form action="<c:url value='/ImageUploadServlet'/> " method="post" enctype="multipart/form-data">
                <input type="hidden" id="path" value="${path}">

                <div class="form-group text-center">
                    <label>文件上传</label>
                </div>
                <div class="form-group">
                    <label>文件</label>
                    <div class="form-group">
                        <input name="imgpath" type="file" class="form-control" style="width:200px;" onChange="preview(this)" />
                    </div>
                </div>
                <div class="form-group">
                    <img width="200px" height="200px" src="/" id="previewimg">
                </div>
                <div class="form-group">
                    <input  type="submit" class="btn btn-primary form-control" value="上传" onclick="javascript:return validateName();"/>
                </div>
                <div class="form-group">
                    <input type="button" id="sure" class = "btn form-control btn-primary" value="确定">
                </div>

            </form>
        </div>
    </div>
</div>
</body>
</html>
