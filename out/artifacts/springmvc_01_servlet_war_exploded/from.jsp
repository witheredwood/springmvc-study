<%--
  Created by IntelliJ IDEA.
  User: 90618
  Date: 2021/5/19
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--创建一个提交表单：提交路径是/hello，以 post 方式提交--%>
<form action="/hello" method="post">
    <input type="text" name="method">
    <input type="submit">
</form>

</body>
</html>
