# springmvc-02-mvc项目中每个类实现的功能说明

## 简单的mvc项目体验
在默认文件夹src/main/java中创建自己的包com.withered.controller，并在该包下编写控制类。
在resource文件下创建一个 springmvc-servlet.xml 配置文件，在该配置文件中添加web项目所需的对象

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- 处理器映射 -->
    <bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"/>
    <!-- 处理器适配器 -->
    <bean class="org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter"/>

    <!-- 配置视图解析器 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" id="InternalResourceViewResolver">
        <!-- 前缀 -->
        <property name="prefix" value="/WEB-INF/jsp/"/>
        <!-- 后缀 -->
        <property name="suffix" value=".jsp"/>
    </bean>

    <!-- 注册 bean -->
    <bean id="/hello" class="com.withered.controller.HelloController"/>

</beans>
```
