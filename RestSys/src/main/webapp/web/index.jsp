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
</head>
<body>

     <div class="container">
         <div class="row">


             <main role="main">

                 <!-- Main jumbotron for a primary marketing message or call to action -->

                 <%--轮播图--%>
                 <div id="demo" class="carousel slide" data-ride="carousel">
                     <!-- 指示符 -->
                     <ul class="carousel-indicators">
                         <li data-target="#demo" data-slide-to="0" class="active"></li>
                         <li data-target="#demo" data-slide-to="1"></li>
                         <li data-target="#demo" data-slide-to="2"></li>
                     </ul>

                     <!-- 轮播图片 -->
                     <div class="carousel-inner">
                         <div class="carousel-item active" >
                             <img width="100%" src="<c:url value='/web/image/huanjing.jpg'/> ">
                             <div class="carousel-caption">
                                 <h3>就餐环境</h3>
                                 <p>舒适的就餐环境给您极致的享受!</p>
                             </div>
                         </div>
                         <div class="carousel-item ">
                             <img width="100%" src="<c:url value='/web/image/jiaankangfood.jpg'/> ">
                             <div class="carousel-caption text-warning">
                                 <h3>健康的食材</h3>
                                 <p>本店甄选优质食材，为您的健康保驾护航!</p>
                             </div>
                         </div>
                         <div class="carousel-item">
                             <img width="100%"  src="<c:url value='/web/image/lotfood.jpg'/>">
                             <div class="carousel-caption ">
                                 <h3>一起聚餐</h3>
                                 <p>丰富的菜品,等待您和你好友一起品尝！</p>
                             </div>
                         </div>
                     </div>

                     <!-- 左右切换按钮 -->
                     <a class="carousel-control-prev" href="#demo" data-slide="prev">
                         <span class="carousel-control-prev-icon"></span>
                     </a>
                     <a class="carousel-control-next" href="#demo" data-slide="next">
                         <span class="carousel-control-next-icon"></span>
                     </a>
                 </div>

                 <%--餐厅介绍--%>
                 <br>
                 <h3>我们的特色</h3>
                 <div class="container mt-5">
                     <!-- Example row of columns -->
                     <div class="row">
                         <div class="col-md-3">
                             <h2>香</h2>
                             <p>食物有5种基本味道，酸甜苦辣咸

                                 唯独香味未被纳入其中

                                 那是因为，香，它来自食物自身的深处，而非厨师的后续加工

                                 一种天然的香，最是难得，是怎么也让人吃不厌、忘不了的最好的味道

                                 这种从还在稻田里起就浑身散发清香的独特稻禾品系可与顶级五常大米相媲美</p>
                         </div>
                         <div class="col-md-3">
                             <h2>补</h2>
                             <p>食、药，不同宗，但同源

                                 当植物在远古也因时代的需求而产生了分工

                                 依然拥有药用能力的香禾糯就成了不可多得的上好食材

                                 除了满足口欲、胃欲

                                 它还含人体必需赖氨酸、维生素B及钙、磷、铁等多种微量元素，能够温暖脾胃，补益中气，对尿频、自汗也有较好的食疗效果

                                 世代侗族人所说的“饭养身”

                                 不仅指吃得饱吃得好，更指吃得健康</p>
                         </div>
                         <div class="col-md-3">
                             <h2>糯</h2>
                             <p>任何一样事物，从最初到成熟都要经过自然和社会的大量磨练

                                 那些经过打磨而依然保持本质不变的，无疑是天地间的精华

                                 香禾糯，从上锅到过夜冷凉

                                 经过旺火的蒸腾，空气中菌分子的裹胁

                                 面对每一种不同的考验，始终色泽洁白、糯性强，保持着良好的口感和柔软度

                                 这都源自香禾糯中较多的支链分子成分，也是它口感糯的原因
                             </p>
                         </div><div class="col-md-3">
                             <h2>净</h2>
                             <p>10月，田野中弥漫着成熟的稻香，收获的季节到了

                                 寨民们依然延续着传统收割方式，用摘禾刀一穗一穗地摘

                                 这是成熟的稻米与人的第一次正式接触

                                 稻民们弯着腰，稻穗们也弯着腰

                                 人和稻都在用这种虔诚的方式向对方致敬

                                 稻民们把收割回来的稻株晾晒在屋外的架子上，让其自然通风、晾晒干后归仓

                                 这样晒干的香禾糯可以保持原滋特有的清香味.</p>

                         </div>
                     </div>

                     <hr>

                 </div> <!-- /container -->

             </main>

         </div>
     </div>

</body>
</html>
