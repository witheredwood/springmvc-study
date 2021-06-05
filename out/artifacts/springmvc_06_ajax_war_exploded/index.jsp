<%--
  Created by IntelliJ IDEA.
  User: 90618
  Date: 2021/5/26
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>

</head>
<body>

<%-- 失去焦点的时候，发起一个请求到后台 --%>
用户名：<input type="text" id="username"/>


<%--  <script src="statics/js/jquery-3.6.0.js"></script>--%>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
<script>
    $(function () {
        <%-- 完成失去焦点时的响应 --%>
        $("#username").blur(function () {
            console.log("blur()------------");
            $.ajax({
                url: "${pageContext.request.contextPath}/a1",
                type: "post",
                data: {"name": $("#username").val()},
                success: function (data, status) {
                    console.log("data=" + data);
                    console.log("status=" + status);
                }
            })
        });
        <%-- 输入框获得焦点 --%>
        $("#username").focus(function () {
            console.log("focus------------");
        });
        <%-- 输入框点击事件 --%>
        $("#username").click(function () {
            console.log("focus------------");
        });
    });
</script>


</body>

</html>
