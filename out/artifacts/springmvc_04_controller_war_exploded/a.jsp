<%--
  Created by IntelliJ IDEA.
  User: 90618
  Date: 2021/5/22
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<%--
<form action="/add/{a}/{b}" method="post">
不能把表单以post的方式提交到这个路径，只能提交到一个确定的路径
--%>
<form action="/add" method="post">
    <input type="text" name="a">
    <input type="text" name="b">
    <input type="submit">
</form>

</body>
</html>
