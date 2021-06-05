# springmvc-07-intercepter项目中每个类实现的功能说明
SpringMVC 的处理器拦截器相当于servlet中的过滤器Filter



## 1. 拦截器的使用

- applicationContext.xml：servlet配置：扫描包、静态资源过滤、json乱码问题、视图解析器、拦截器。

```xml
<!-- 拦截器配置 -->
<mvc:interceptors>
    <mvc:interceptor>
        <!-- 包括这个请求下面的所有的请求 -->
        <mvc:mapping path="/user/**"/>
        <bean class="com.withered.config.LoginInterceptor"/>
    </mvc:interceptor>
</mvc:interceptors>
```

- MyInterceptor：自己编写拦截器。一般只用重写处理前.
- TestController：处理前端的请求。
```java
// 接收前端的/t1请求后，返回一个字符串"ok"
@GetMapping("/t1")
public String test() {
    System.out.println("TestController==》test()执行了-------");
    return "ok";
}
```


## 2. 登录判断验证

> 前端页面

在WEB-INF下的所有页面或者资源，只能通过controller,或者servlet访问。

- index.jsp：入口页面。从该页面进入到首页和登录页面。
- main.jsp：首页页面。
- login.jsp：登录页面。

> 后端处理

- LoginController：登录请求处理，返回前端的消息会被视图解析器处理。
- LoginInterceptor：登录功能中拦截器设置。