<%--
  Created by IntelliJ IDEA.
  User: 90618
  Date: 2021/5/26
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <!-- 引入 Bootstrap -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>修改书籍</small>
                </h1>
            </div>
        </div>
    </div>

    <form action="${pageContext.request.contextPath}/book/updateBook" method="post">
        <%-- 提交修改的sql语句， 但是修改失败，问题：1.事务问题；
        2.sql是否执行成功。
            sql执行失败，修改未完成。解决办法：前端传递隐藏域
        --%>
        <input type="hidden" name="bookId" value="${aBook.bookId}">
        <div class="form-group">
            <label>书籍名称：</label>
            <input type="text" name="bookName" value="${aBook.bookName}" class="form-control" required>
        </div>
        <div class="form-group">
            <label>书籍数量：</label>
            <input type="text" name="bookCounts" value="${aBook.bookCounts}" class="form-control" required>
        </div>
        <div class="form-group">
            <label>书籍描述：</label>
            <input type="text" name="detail" value="${aBook.detail}" class="form-control" required>
        </div>
        <div class="form-group">
            <input type="submit" class="form-control" value="确定">
        </div>
    </form>

</div>

</body>
</html>
