# springmvc-04-controller项目中每个类实现的功能说明

## 1. 实现了编写控制类的几种方法：
- Controller1：实现了 Controller 接口的类，一个控制器只能实现一个类。
- Controller2：使用注解@Controller将类变成控制类，被Spring接管。
- Controller3：使用注解@RequestMapping("/c3/t1")，实现该类方法处理的请求地址。

## 2. ResrFul风格
- RestFulController：实现url地址的ResrFul风格，参数用/分开。例子：http://localhost:8080/add/1/2
- 注：
不同方法的请求地址可以相同，但是，相同的请求地址不能实现重写。

## 3. 视图解析器
- ModelTest：实现视图的跳转。视图跳转需要视图解析器和不需要视图解析器的实现方法，以及转发和重定向的实现方法。

## 4.接收前端的参数
- UserController：从前端接收的参数（基本类型和对象）的接收方法。

## 5. 接收前端的参数
- EncodingController：解决中文乱码问题。推荐使用Spring自带的乱码过滤器。在web.xml中注册该过滤器即可。



