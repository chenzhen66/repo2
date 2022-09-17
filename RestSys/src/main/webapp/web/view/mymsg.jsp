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
    <style>
    .form-inlinep{
        margin-top: 5%;
        padding-bottom: 1%;
        border-bottom: #4ef10c 1px solid;
    }
    </style>
    <script>

        ${alert}
        $(function () {
            $("#${modalid}").modal("show")
        })
        /*
        * 调出模态框
        * */
        function alertmsg() {
            $("#exampleModalCenter").modal("show")
        }
        /*
        * 通过Ajax获取验证码
        * 设置验证码过期时间为60秒
        * 验证码倒计时间内不可点击
        * */
        function getcode(){
            $.post(
                "${pageContext.request.contextPath}/CodeServlet",
                {"action":'addCode'},
                function (data) {
                   console.log("获取验证码成功")
                },"json"
            )

            var time = 60;
            if(time==60){//如果不加入该判断，则会出现在倒计时期间不断的点击发生不断的加快（其实就是你点了多少次就重复多少次该函数）的问题，用setTimeout（）方法不加此判断也是一样的
                var time1=setInterval(function(){
                    if(time==0){
                        $("#sendsms").html("重新发送");
                        $("#sendsms").removeAttr("disabled");
                        time=60;
                        clearInterval(time1);
                    }else{
                        $("#sendsms").html("重新发送("+time+")");
                        $("#sendsms").attr('disabled',true);
                        time--;
                    }
                }, 1000);
            }

        }
        /*
        * 如密码验证通过，提交修改密码申请
        * */
        function submitform() {
            if (validatePSD()){
                $("#form1").submit();
            }
        }
        /*
        * 验证密码是否输入一致
        * */
        function validatePSD() {
            let opsd = $("#newpsd").val().trim();
            let opsd2 = $("#anewpsd").val().trim();
            if (validateNPSD()){
                $("#code").text("密码输入不一致");
                if (opsd==opsd2){
                    $("#code").text("√密码输入一致");
                    return true;
                }
            }
            return false;
        }
        /*
        * 校验要修改的姓名
        * */
        function validateName() {
            let name= $("#username").val();
            $("#namemsg").text("√")
            if (name.trim()==null || name.trim()===""){
                $("#namemsg").text("姓名不可为空")
                return false;
            }
            return true;
        }
        /*
        * 如果姓名不为空，提交表单，更新
        * */
        function updatepsd() {
            if (validateName()){
                $("#formpsd").submit();
            }else {
                alert("姓名不可为空")
            }
        }

        /*
        * 校验新密码不可为空
        * 为空返回false
        * 不为空返回true
        * */
        function validateNPSD() {
            let npsd = $("#newpsd").val()
            let psd = $("#anewpsd").val()
            if (npsd.trim() == "" || npsd.trim() == null){
                $("#code").text("密码不可为空！")
                return false;
            }else if (psd.trim() == "" || psd.trim() == null){
                $("#code").text("密码不可为空！")
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
   <div class="container">
       <div class="row mt-5" style="font-size: 24px;">
           <form id="formpsd" class="col-md-12 mt-5" action="<c:url value='/UserServlet'/>" method="post">
               <input type="hidden" name="action" value="updateMsg">
               <div class="col-md-7 m-auto pl-5" style="border: #333333 1px solid">
                   <div class="form-inlinep">
                       <span class="col-md-3">邮箱:</span>${user.getMail()}
                   </div>
                   <div class="form-inlinep">
                        <span class="col-md-3">姓名:</span><input id="username" onblur="validateName()" maxlength="20" type="text" name="name" value="${user.getUser_name()}">
                        <span class="text-danger" id="namemsg"></span>
                   </div>
                   <div class="form-inlinep">
                       <span class="col-md-3">性别:</span>
                       <c:choose>
                           <c:when test="${user.getSex()=='男'}">
                               <input checked name="sex" type="radio" value="男">男
                               <input name="sex" type="radio" value="女">女
                           </c:when>
                           <c:otherwise>
                               <input name="sex" type="radio" value="男">男
                               <input checked name="sex" type="radio" value="女">女
                           </c:otherwise>
                       </c:choose>

                   </div>
                   <div class="form-group mt-2">
                       <input class="col-md-9 btn btn-success" type="button" onclick="updatepsd()" value="修改信息"><br>
                       <a style="font-size: 14px" onclick="alertmsg()" class="text-decoration-none">修改密码</a>
                   </div>

               </div>
               <div class="col-md-3"></div>
           </form>
       </div>
   </div>
   <!-- 模态框 -->
   <div class="modal fade" id="exampleModalCenter" tabindex="-1" data-backdrop="static" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
       <div class="modal-dialog modal-dialog-centered" role="document">
           <div class="modal-content">
               <div class="modal-header">
                   <h5 class="modal-title" id="exampleModalCenterTitle">更改密码</h5>
                   <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                       <span aria-hidden="true">&times;</span>
                   </button>
               </div>
               <div class="modal-body">
                   <form id="form1" class="col-md-12" method="post" action="<c:url value='/UserServlet'/> ">
                       <input type="hidden" name="action" value="updatepsd">
                       <div class="form-group">
                           <span class="col-md-3">新密码： &nbsp; </span><input maxlength="20" onblur="validateNPSD()" id="newpsd" type="password" name="newpassword" placeholder="newPsssword">
                       </div>
                       <div class="form-group">
                           <span class="col-md-3">确认密码：</span><input maxlength="20" onblur="validatePSD()" id="anewpsd" type="password" name="newpassword1" placeholder="newPssswordagain">
                       </div>
                       <div class="form-group">
                           <span class="col-md-3">验证码： &nbsp; </span><input maxlength="6" type="text" name="code" placeholder="Code">
                           <br><span  class="text-danger" style="margin-left: 25%" id="code"></span>
                       </div>
                   </form>
                   <button style="font-size: 14px;position: absolute;left: 67%;top: 60%" id="sendsms" onclick="getcode()" class="btn border-success">获取验证码</button>
               </div>
               <div class="modal-footer">
                   <button type="button" class="btn btn-primary" onclick="submitform()" id="queding" >修改</button>
               </div>
           </div>
       </div>
   </div>
</body>
</html>
