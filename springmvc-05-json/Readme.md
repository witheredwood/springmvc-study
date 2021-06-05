# springmvc-05-json项目中每个类实现的功能说明

## 1. 实现json的使用
> jsontest.html：实现 json 对象和 js 对象的转换。

```javascript
var json = JSON.stringify(user);
var obj = JSON.parse(json);
```
> 解决 json 乱码问题：

在 springmvc-servlet.xml 中统一配置
```xml
    <!-- json 乱码问题配置-->
    <mvc:annotation-driven>
        <mvc:message-converters register-defaults="true">
            <bean class="org.springframework.http.converter.StringHttpMessageConverter">
                <constructor-arg value="utf-8"/>
            </bean>
            <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
                <property name="objectMapper">
                    <bean class="org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean">
                        <property name="failOnEmptyBeans" value="false"/>
                    </bean>
                </property>
            </bean>
        </mvc:message-converters>
    </mvc:annotation-driven>
```

## 2. Jackson 的使用
> UserController：向前端返回一个json字符串，不会被视图解析器处理。

## 3. 日期格式
> UserController：将日期格式化为字符串返回到前端。

> JsonUtils：将日期格式化编写为一个工具类。