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
        $(function () {

            $(".modal-body").text("${msg}");
            $("${modalid}").modal("show");


            $("#all").click(function(){
                if(this.checked){
                    $(".form-check-input").prop("checked",true);
                }
                else{
                    $(".form-check-input").prop("checked",false)
                }
                updatecart();
            });

            $(".form-check-input").click(function () {

                updatecart();
            })

            $(".reduce").click(function () {
                let foodid = parseInt(this.name);
                let count = parseInt($(this).next("span").text().trim())-1;
                if (count<=0){
                    count=1;
                    $(".modal-body").text("最少购买一份，如果不需要请删除");
                    $("#exampleModalCenter").modal("show");
                }
                var btn =$(this);
                $.post(
                    "${pageContext.request.contextPath}/CartServlet",
                    {"count":count,"action":"updateCartCount","foodid":foodid},
                    function (data) {
                        btn.next("span").text(data)

                        let str = ".inpt"+foodid;
                        let a = $(str).val().split(",");
                        a[1]=count
                        let b=a[0]+","+a[1]+","+a[2];
                        $(str).val(b)
                        
                       updatecart();

                    },"json"
                )

            })


            $(".add").click(function () {
                let foodid = parseInt(this.name);
                let count = parseInt($(this).prev("span").text().trim())+1;
                var btn =$(this);
                $.post(
                    "${pageContext.request.contextPath}/CartServlet",
                    {"count":count,"action":"updateCartCount","foodid":foodid},
                    function (data) {
                        btn.prev("span").text(data);
                        let str = ".inpt"+foodid;
                        let a = $(str).val().split(",");
                        a[1]=count
                        let b=a[0]+","+a[1]+","+a[2];
                        $(str).val(b)
                        updatecart();
                    },"json"
                )
            })


        })
        function updatecart() {

            var countNum = 0;
            var priceCount = 0.0;

            $("input[name='text']:checked").each(function () {
                var list =$(this).val().split(",");//id,数量,价格
                let totalcountNum=parseInt(list[1].trim())
                countNum+=totalcountNum;
                priceCount+=parseFloat(list[2].trim()).toFixed(2)*totalcountNum;
            })
            $(".countNum").text(" "+countNum+" ");
            $("#money").text(" "+parseFloat(priceCount).toFixed(1)+" ");
        }

        function deleteOne(name) {
            $(".modal-body").text("确定要删除吗?");
            $("#exampleModalCenter").modal("show");

            $("#queding").click(function () {
                location.href="CartServlet?action=deleteone&cartid="+name;
            })
        }

        /*多选删除*/
        function deleteMore() {
            $("#queding").off()//取消按钮绑定事件
            var  m = 0;
            $("input[name='text']:checked").each(function () {
                m++;
            })
            if (m==0){
                $(".modal-body").text("请先选中");
                $("#exampleModalCenter").modal("show");
            }else {
                $(".modal-body").text("确定要删除吗?");
                $("#exampleModalCenter").modal("show");
                $("input[name='action']").val("DeleteMore")

                $("#queding").click(function () {
                    $(".form").submit();
                })
            }

        }

        /*下单*/
        function BuyOrder() {
            $("#queding").off()//取消按钮绑定事件
            var  m = 0;
            $("input[name='text']:checked").each(function () {
                m++;
            })
            if (m==0){
                $(".modal-body").text("请先选中");
                $("#exampleModalCenter").modal("show");
            }else {
                $("input[name='action']").val("BuyOrder")
                $(".modal-body").html("<h4 class='text-left'>备注：</h4><textarea maxlength='10' name='comments' class='col-md-12' style='height: 100px' type='text' ></textarea>");
                $("#exampleModalCenterTitle").text("备注")
                $("#exampleModalCenter").modal("show");
                $("#queding").click(function () {
                    $(".form").submit();
                })
            }
        }

    </script>
</head>
<body>

<div class="container">
    <div class="row mt-4 text-center">
        <form class="col-md-12 form"  action="CartServlet" method="post">
            <input type="hidden" name="action" value="">
            <table class="table table-cell table-responsive-md mb-5">
                <thead class="text-center">
                <c:choose>
                    <c:when test="${list.size()==0}">
                        <h1 class="h1 text-danger mt-5">您的购物车中没有餐品</h1>
                    </c:when>
                    <c:otherwise>
                        <tr class="navbar-fixed-top bg-primary sticky-top text-white">
                            <th scope="col"><input type="checkbox"  id="all" class="form-check-input">全选</th>
                            <th scope="col">商品信息</th>
                            <th scope="col">商品名称</th>
                            <th scope="col">数量</th>
                            <th scope="col">单价</th>
                            <th scope="col">操作</th>
                        </tr>
                    </c:otherwise>
                </c:choose>
                </thead>
                <tbody class="text-center">

                <c:forEach items="${list}" var="item">
                    <tr>
                        <th class="mt-5 chck" scope="row">
                            <input type="checkbox"  name="text" class="form-check-input inpt${item.food.id}" value="${item.cart_id},${item.count},${item.food.price},${item.food.id}">
                        </th>
                        <td><img width="100px" height="80px" src="<c:url value='/${item.food.imgpath}'/> "></td>
                        <td class="mt-2" >${item.food.name}</td>
                        <td>
                            <div class="row" style="margin-left: 36%">
                                <a class="btn bg-white border-dark reduce"  name="${item.food.id}">-</a>
                                <span class="col-md-2 span mt-2"> ${item.count}</span>
                                <a class="btn bg-white border-dark ml-1 add" name="${item.food.id}">+</a>
                            </div>
                        </td>
                        <td class="mt-2 text-danger" >${item.food.price}</td>
                        <td class="mt-2" ><a class="btn btn-info" name="${item.cart_id}" onclick="deleteOne(this.name)" >删除</a> </td>
                    </tr>
                </c:forEach>

        </table>
        <footer class="container-fluid fixed-bottom bg-white mb-1" >
            <div class="row w-75 text-white m-auto bg-dark" style="height: 60px">
                <div class="col-md-3" style="margin: auto">
                    <img style="position: relative" width="60px" height="60px" src="<c:url value='/web/image/cart.png'/> ">
                    <div class="carousel-caption">
                        <p class="text-warning font-weight-bold countNum" style="position: absolute;top: 0;left: 15%;">0</p>
                    </div>
                    共计:<span  class="text-warning countNum" > 0 </span>件
                </div>
                <div class="col-md-3 m-auto">
                    <div>
                        共计金额:<span class="text-warning" id="money"> 0.0 </span>元
                    </div>

                </div>
                <div class="col-md-3 text-right" style="margin: auto">
                    <input type="button" onclick="deleteMore()" class="col-md-12 btn btn-info" value="删除">
                </div>
                <div class="col-md-3 text-right" style="margin: auto">
                    <input type="button" onclick="BuyOrder()" class="col-md-12 btn btn-info" value="下单">
                </div>
            </div>
        </footer>
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
                            提示信息
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" id="queding" data-dismiss="modal">确定</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>



</body>
</html>
