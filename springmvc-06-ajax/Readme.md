# springmvc-06-ajax项目中每个类实现的功能说明

## 1. ajax的使用
> applicationContext.xml：servlet配置：扫描包、静态资源过滤、json乱码问题、视图解析器。



>  test.html：iframe页面无刷新。



## 2. 通过Ajax向后端传递数据

>  前端页面

- index.jsp：输入框失去焦点事件、点击事件。

```jsp
<body>

  <%-- 失去焦点的时候，发起一个请求到后台 --%>
  用户名：<input type="text" id="username"/>

<%--  <script src="statics/js/jquery-3.6.0.js"></script>--%>
  <script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
  <script>
    $(function () {
      <%-- 完成失去焦点时的响应 --%>
      $("#username").blur(function(){
        console.log("blur()------------");
        <%-- 通过Ajax向前端传递输入框中的数据 --%>
        $.ajax({
          url: "${pageContext.request.contextPath}/a1",
          type: "post",
          data: {"name":$("#username").val()},
          success: function (data, status) {
            console.log("data="+data);
            console.log("status="+status);
          }
        })
      });
      
      <%-- 输入框获得焦点 --%>
      $("#username").focus(function(){
        console.log("focus------------");
      });
        
      <%-- 输入框点击事件 --%>
      $("#username").click(function(){
        console.log("focus------------");
      });
    });
  </script>

</body>
```

> 后端

- AjaxController：根据请求地址处理前端传来的数据。

```java
// 判断前端传递的参数是否是withered，并向前端返回消息。
@RequestMapping("/a1")
public void a1(String name, HttpServletResponse response) throws IOException {
    System.out.println("a1.param=>"+name);
    if ("withered".equals(name)) {
        response.getWriter().print("true");
    } else {
        response.getWriter().print("false");
    }
}
```



##  3. 从后端异步获取数据

> 前端

- test2.jsp：从后端获取数据，并在页面上显示。

```jsp
<input type="button" value="加载数据" id="btn">
<table>
    <tr>
        <td>姓名</td>
        <td>年龄</td>
        <td>性别</td>
    </tr>
    <tbody id="content">
    <%-- 数据：从后台获取 --%>

    </tbody>
</table>

<script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
<script>
    $(function () {
        $("#btn").click(function () {
            <%-- 通过Ajax异步传递数据 --%>
            $.post("${pageContext.request.contextPath}/a2", function (data) {
                let html = "";

                for (let i = 0; i < data.length; i++) {
                    html += "<tr>" +
                        "<td>" + data[i].name +"</td>" +
                        "<td>" + data[i].age +"</td>" +
                        "<td>" + data[i].sex +"</td>" +
                        "</tr>"
                }

                $("#content").html(html);
            });
        })
    });

</script>

</body>
```

> 后端

- AjaxController：根据请求地址向前端返回数据。

```java
@RequestMapping("/a2")
public List<User> a2() {
    List<User> userList = new ArrayList<User>();

    userList.add(new User("java", 1, "男"));
    userList.add(new User("java2", 2, "男"));
    userList.add(new User("java3", 3, "男"));
    userList.add(new User("java3", 4, "男"));

    return userList;
}
```



## 4. 用户登录

> 前端

- login.jsp：登录页面。输入用户名和密码，显示是否输入正确。

```jsp
$("#name").blur(function () {
    console.log("name-blur---------");
    console.log("${pageContext.request.contextPath}/a3");
    $.post({
        url: "${pageContext.request.contextPath}/a3",
        data: {"name":$("#name").val()},
        success: function (data) {
            if (data.toString() === 'ok') {
                $("#userInfo").css("color", "green");
            } else {
                $("#userInfo").css("color", "red");
            }
            $("#userInfo").html(data);
        }
    })
});
```

> 后端

- AjaxController：根据请求地址向前端返回数据。

```java
@RequestMapping("/a3")
public String a3(String name, String pwd) {
    String msg = "";
    if (name != null) {
        // admin 这些数据应该在数据库中查
        if ("admin".equals(name)) {
            msg = "ok";
        } else {
            msg = "用户名有误";
        }
    }
    if (pwd != null) {
        // 123456 这些数据应该在数据库中查
        if ("123456".equals(pwd)) {
            msg = "ok";
        } else {
            msg = "密码有误";
        }
    }
    return  msg;
}
```

