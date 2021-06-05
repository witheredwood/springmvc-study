<%--
  Created by IntelliJ IDEA.
  User: 90618
  Date: 2021/5/23
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <style>
        a {
            text-decoration: none;
            color: black;
            font-size: 18px;
        }

        h3 {
            width: 180px;
            height: 38px;
            margin: 100px auto; /* 设置边距 */
            text-align: center; /* 设置文字位置 */
            line-height: 38px; /* 设置字体高度 */
            background: aquamarine; /* 设置背景颜色 */
            border-radius: 5px; /* 设置形状圆角 */
        }
    </style>
</head>
<body>
<h3>
    <a href="${pageContext.request.contextPath}/book/allBook">进入书籍展示页面</a>
</h3>
</body>
</html>
