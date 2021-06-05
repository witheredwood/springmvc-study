# springmvc-03-annotation项目中每个类实现的功能说明

## 使用注解实现控制类
在resource文件下创建一个 springmvc-servlet.xml 配置文件，在该配置文件中添加启动注解的配置

```xml
<context:component-scan base-package="com.wmq.controller"/>
<mvc:default-servlet-handler/>
<mvc:annotation-driven/>
```
