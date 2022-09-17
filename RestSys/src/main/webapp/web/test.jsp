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
<form action="<c:url value='/ImageUploadServlet'/> " enctype="multipart/form-data" method="post">
    <input type="hidden" name="action" value="test">
    <input name="a" type="text">
    <input name="b" type="text">
    <input name="c" type="file">
    <input type="text">
    
</form>

</body>
</html>
