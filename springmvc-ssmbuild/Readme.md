# springmvc-ssmbuild：ssm 框架的整合

本项目大部分使用的配置，只使用了几个注解。



## 1. 搭建框架

### 1.1 基本环境搭建
- 创建 maven 项目：导入所需的依赖。在 pom.xml 中添加需要的依赖和Maven资源过滤。
  Maven 依赖查询网址：[https://mvnrepository.com/](https://mvnrepository.com/)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>SpringMVC</artifactId>
        <groupId>org.example</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>springmvc-ssmbuild</artifactId>

    <!-- 添加依赖: junit5，数据库驱动，连接池，servlet，jsp，mybatis，mybatis-spring，spring，lombok，log4j，aspectjweaver -->
    <dependencies>
        <!-- junit5 -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>RELEASE</version>
            <scope>test</scope>
        </dependency>
        <!-- 数据库驱动 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.24</version>
        </dependency>
        <!-- 数据库连接池 -->
        <dependency>
            <groupId>com.mchange</groupId>
            <artifactId>c3p0</artifactId>
            <version>0.9.5.5</version>
        </dependency>

        <!-- Mybatis -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.7</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>2.0.6</version>
        </dependency>

        <!-- Spring -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.3.6</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.3.7</version>
        </dependency>

        <!-- servlet - jsp -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>2.5</version>
        </dependency>
        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>jsp-api</artifactId>
            <version>2.2</version>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>

        <!-- 导入 lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.20</version>
        </dependency>

        <!-- 日志log4j -->
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.12</version>
        </dependency>


        <!-- 横切的包aspectjweaver -->
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.9.6</version>
        </dependency>

    </dependencies>

    <!-- Maven 资源过滤：静态资源导出问题 -->
    <build>
        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>false</filtering>
            </resource>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>false</filtering>
            </resource>
        </resources>
    </build>

</project>
```

### 1.2 整合Mybatis层

使用Mybatis处理与数据库相关的操作。

> 搭建web框架

在 `src/main/java` 下创建以下的包：

- com.withered.controller：控制层的包。
- com.withered.dao：数据访问层的包。
- com.withered.pojo：数据（实体类）的包。
- com.withered.service：业务处理层的包。

> 添加数据源

数据源为 ssmbuild 数据库下的 books 表。创建表之后导入IDEA项目中。

> 创建 Myabatis 的配置文件
在 `src/main/resources` 文件下创建 mybatis-config.xml。

- mybatis-config.xml： Mybatis 核心配置。
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">

<configuration>
    
    <!-- 设置执行sql语句后打印结果 -->
    <settings>
        <setting name="logImpl" value="STDOUT_LOGGING" />
    </settings>

    <!-- 配置数据源，交给 spring 去做-->
    <typeAliases>
        <package name="com.withered.pojo"/>
    </typeAliases>

    <!-- 注册数据访问层dao层中的对象，下面创建对象后再注册 -->
    <!--
    <mappers>
        <mapper class="com.withered.dao.BookMapper"/>
    </mappers>
	-->

</configuration>
```

>  servlet 的配置文件框架
在 `src/main/resources` 文件下创建 applicationContext.xml 。

- applicationContext.xml： servlet 核心配置框架，servlet会以该文件为入口。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- 使用配置将各层的配置文件注入到Spring中 ，也可以使用注解注入到Spring中-->
    <import resource="classpath:spring-dao.xml"/>
    <import resource="classpath:spring-service.xml"/>
    <import resource="classpath:spring-mvc.xml"/>

</beans>
```

> 配置数据库连接的配置文件 
在 `src/main/resources` 文件下创建 database.properties。

- database.properties：连接到数据库ssmbuild。

```properties
jdbc.driver=com.mysql.jdbc.Driver
# 如果使用的是Mysql 8.0+，要增加一个时区的设置
jdbc.url=jdbc:mysql://localhost:3306/ssmbuild?userSSL=false&userUnicode=true&characterEncoding=utf-8&serverTimeZone=UTC
jdbc.username=root
jbdc.password=1234567
```

> 定义实体类 Books
在 `src/main/com.withered.pojo` 下创建 books.java。

- Books：实体类数据。使用注解注入。

```java
// 定义实体类
@Data
@AllArgsConstructor  // 有参构造
@NoArgsConstructor  // 无参构造
public class Books {
    private int bookId;
    private String bookName;
    private int bookCounts;
    private String detail;
}
```

> 编写数据层 dao 层
在 `src/main/com.withered.dao` 下创建 `BookMapper.java` 和 `BookMapper.xml`。

- BookMapper：接口。

```java
//做一些接口操作
public interface BookMapper {
    // 增加一本书
    int addBook(Books books);

    // 删除一本书
    int deleteBookById(@Param("bookId") int id);

    // 更新一本书
    int updateBook(Books books);

    // 查询一本书
    Books queryBookById(@Param("bookId") int id);

    // 查询全部的书
    List<Books> queryAllBook();
}
```

- BookMapper.xml：一个 mapper 对应一个接口。简单的来说，就是编写sql语句。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!-- 一个 mapper 对应一个接口-->
<mapper namespace="com.withered.dao.BookMapper">

    <insert id="addBook" parameterType="Books">
        insert into ssmbuild.books (bookName, bookCounts, detail)
        values (#{bookName}, #{bookCounts}, #{detail});
    </insert>

    <delete id="deleteBookById" parameterType="int">
        delete from ssmbuild.books where bookId = #{bookId};
    </delete>

    <update id="updateBook" parameterType="Books">
        update ssmbuild.books
        set bookName=#{bookName}, bookCounts=#{bookCounts}, detail=#{detail}
        where bookId=#{bookId};
    </update>

    <select id="queryBookById" resultType="Books">
        select * from ssmbuild.books where bookId = #{bookId};
    </select>

    <select id="queryAllBook" resultType="Books">
        select * from ssmbuild.books;
    </select>

</mapper>
```

写完 `BookMapper.xml` 后要在 `mybatis-config.xml` 中注册 `BookMapper` 对象。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">

<configuration>
	<!-- 注册数据访问层dao层中的对象 -->
    <mappers>
        <mapper class="com.withered.dao.BookMapper"/>
    </mappers>
</configuration>
```

> 编写业务层
在 `src/main/com.withered.service` 下创建 `BookService.java` 和 `BookServiceImp.java`。

- `BookService`：业务层接口。

```java
// 业务层接口
public interface BookService {
    // 增加一本书
    int addBook(Books books);

    // 删除一本书
    int deleteBook(int id);

    // 更新一本书
    int updateBook(Books books);

    // 查询一本书
    Books queryBookById(int id);

    // 查询全部的书
    List<Books> queryAllBook();
}
```

- `BookServiceImp`：业务层接口的实现类。业务层（service层）调用 数据访问层（dao层）。

```java
public class BookServiceImp implements BookService{
    // 业务层（serviece层）调用 数据访问层（dao层）
    private BookMapper bookMapper;

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public int addBook(Books books) {
        return bookMapper.addBook(books);
    }

    public int deleteBook(int id) {
        return bookMapper.deleteBookById(id);
    }

    public int updateBook(Books books) {
        return bookMapper.updateBook(books);
    }

    public Books queryBookById(int id) {
        return bookMapper.queryBookById(id);
    }

    public List<Books> queryAllBook() {
        return bookMapper.queryAllBook();
    }

    public List<Books> queryBookByName(String bookName) {
        return bookMapper.queryBookByName(bookName);
    }
}
```



### 1.3 整合Spring层

#### 1.3.1 整合Dao层
> 编写dao层配置文件 
在 `src/main/com.withered.dao` 下创建 `spring-dao.xml`。

- `spring-dao.xml`：关联数据库配置文件，配置连接池，配置 sqlSessionFactory 和配置dao接口扫描包。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd">

    <!-- 1. 关联数据库配置文件-->
    <context:property-placeholder location="classpath:database.properties"/>

    <!--  2. 连接池 -->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="${jdbc.driver}"/>
        <property name="jdbcUrl" value="${jdbc.url}"/>
        <property name="user" value="${jdbc.username}"/>
        <property name="password" value="${jbdc.password}"/>

        <!-- c3p0 连接池的私有属性 -->
        <property name="maxPoolSize" value="30"/>
        <property name="minPoolSize" value="10"/>
        <!-- 关闭连接后不自动commit -->
        <property name="autoCommitOnClose" value="false"/>
        <!-- 获取连接超时时间 -->
        <property name="checkoutTimeout" value="10000"/>
        <!-- 当获取连接失败重试次数 -->
        <property name="acquireRetryAttempts" value="2"/>
    </bean>

    <!-- 3. sqlSessionFactory -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <!-- 绑定 Mybatis 的配置文件 -->
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
    </bean>

    <!-- 配置dao接口扫描包，动态的实现了Dao 接口可以注入到Spring容器中 -->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <!-- 注入 sqlSessionFactory -->
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
        <!-- 要扫描的dao包 -->
        <property name="basePackage"  value="com.withered.dao"/>
    </bean>
</beans>
```

> 手动注入实现类(不推荐)
- `BookMapperImpk`：手动注入Spring的实现类。如果不用动态注入Spring，手动注入Spring中，需要编写一个实现类。

#### 1.3.2 整合Service层
> 编写service层配置文件 
在 `src/main/resources` 下创建 `spring-service.xml`。

 - `spring-service.xml`：扫描service下的包，将所有的业务类注入到Spring中，配置声明式事务配置和aop 事务支持。

 ```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd
        http://www.springframework.org/schema/tx
        http://www.springframework.org/schema/tx/spring-tx.xsd">

    <!-- 1. 扫描service下的包 -->
    <context:component-scan base-package="com.withered.service"/>

    <!-- 2. 将所有的业务类注入到Spring，可以通过配置，或者注解实现-->
    <bean id="BookServiceImp" class="com.withered.service.BookServiceImp">
        <property name="bookMapper" ref="bookMapper"/>
    </bean>

    <!-- 3. 声明式事务配置 -->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <!-- 注入数据源 -->
        <property name="dataSource" ref="dataSource"/>
    </bean>

    <!-- 4. aop 事务支持 -->
    <!-- 配置事务通知 -->
    <tx:advice id="txAdvice" transaction-manager="transactionManager" >
        <!-- 给方法配置事务 -->
        <tx:attributes>
            <!-- 所有的增删改查操作 -->
            <tx:method name="*" propagation="REQUIRED"/>
        </tx:attributes>
    </tx:advice>
    
    <!-- 配置事务切入 -->
    <aop:config>
        <!-- com.withered.dao 包下的所有内容 -->
        <aop:pointcut id="txPointCut" expression="execution(* com.withered.dao.*.*(..))"/>
        <aop:advisor advice-ref="txAdvice" pointcut-ref="txPointCut"/>
    </aop:config>
</beans>
 ```

#### 1.3.3 servlet配置文件
> 编写servlet总配置文件 
在 `src/main/resources` 下中的 `applicationContext.xml`。

- `applicationContext.xml`：扫描service下的包，将所有的业务类注入到Spring中，配置声明式事务配置和aop 事务支持。

```xml
<!-- 使用配置注入到Spring中 ，也可以使用注解注入到Spring中-->
<import resource="classpath:spring-dao.xml"/>
<import resource="classpath:spring-service.xml"/>
<import resource="classpath:spring-mvc.xml"/>
```

> 使用注解注入到Spring中

如果使用注解将类注入到Spring中，而不是使用配置将类注入到Spring中，则在要注入的类上加上注解即可。

- `BookServiceImp`：加 @Service 和 @Autowired 注解。

```java
@Service
public class BookServiceImp implements BookService{
    // 业务层（serviece层）调用 数据访问层（dao层）
	@Autowired
    private BookMapper bookMapper;
    
    // 其余方法
    ...
}
```



### 1.4 整合SpringMVC

> 添加Web支持

项目右键 --> Add Framework Support

> 在 web.xml 中设置DispatchServlet、乱码过滤、Session

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
         http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <!-- DispatchServlet -->
    <servlet>
        <servlet-name>springmvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:applicationContext.xml</param-value>  
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>springmvc</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

    <!-- 乱码过滤 -->
    <filter>
        <filter-name>encodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>utf-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

    <!-- Session -->
    <session-config>
        <session-timeout>15</session-timeout>
    </session-config>
</web-app>
```

> springmvc配置
在 `src/main/resources` 下创建 `spring-mvc.xml`。

- `spring-mvc.xml`：注解驱动、静态资源过滤、扫描controller包，添加视图解析器。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/mvc
        https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!-- 1. 注解驱动-->
    <mvc:annotation-driven/>
    <!-- 2. 静态资源过滤-->
    <mvc:default-servlet-handler/>
    <!-- 3. 扫描包：controller -->
    <context:component-scan base-package="com.withered.controller"/>

    <!-- 4. 视图解析器 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/jsp/"/>
        <property name="suffix" value=".jsp"/>
    </bean>
    
</beans>
```

基本框架整合完成之后，开始编写业务代码。以下为业务代码编写的过程。



## 2. 查询全部书籍业务

> 编写后端接口

在 `src/main/java/com.withered.controller` 下创建 `BookController.java`。

- `BookController`：查询全部的书籍。

```java
@Controller
@RequestMapping("/book")
public class BookController {
    // controller 调用 service 层
    @Autowired
    @Qualifier("BookServiceImp")  // 加载指定的类
    private BookService bookService = new BookServiceImp();

    // 查询全部的书籍，并返回到书籍展示页面
    @RequestMapping("/allBook")
    public String list(Model model) {
        List<Books> list = bookService.queryAllBook();
        model.addAttribute("list", list);
        return "allBook";
    }
}
```

在 `src/test/java` 下创建 `TestBean.java`，测试sql语句是否能够执行成功。

- `TestBean`：测试。这里使用junit5进行测试。

```java
public class TestBean {
    @Test
    public void test() {
    // 获取servlet配置文件的路径
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    // 获取BookServiceImp类的bean
    BookService bookServiceImp = context.getBean("BookServiceImp", BookService.class);
    // 调用sql语句，sql是否执行成功
    for (Books books: bookServiceImp.queryAllBook()) {
    System.out.println(books);
    }
}
```

> 美化前端页面

在 `web/WEB-INF/jsp` 下创建 `allBook.jsp`。

- `index.jsp`：首页。
- `allBook.jsp`：书籍展示页面。使用Bootstrap美化页面。

```html
 <!-- 引入 Bootstrap -->
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
```



## 3. 添加书籍业务

> 前端页面

在 `web/WEB-INF/jsp` 下创建 `addBook.jsp`。

- `allBook.jsp`：书籍展示页面。在书籍展示页面添加“新增书籍”的按钮。
- `addBook.jsp`：添加书籍页面。

> 后端controller层处理

- `BookController`：在控制类中添加到增加书籍页面的跳转，以及接收到添加书籍的请求处理过程。

```java
// 从书籍展示页面跳转到增加书籍页面
@RequestMapping("/toAddBook")
public String toAddPaper() {
    return "addBook";
}

// 接收添加书籍的请求，处理添加书籍的请求。
@RequestMapping("/addBook")
public String addPaper(Books books) {
    System.out.println("addBook=>"+books);
    bookService.addBook(books);  // 将该本书籍添加到数据库中
    return "redirect:/book/allBook";  // 重定向，实现请求复用
}
```



## 4. 修改书籍业务

> 前端页面

在 `web/WEB-INF/jsp` 下创建 `updateBook.jsp`。

- `allBook.jsp`：书籍展示页面。添加修改书籍的链接。
- `updateBook.jsp`：修改书籍信息的页面。需要通过隐藏域将要修改的书籍id传递到后端。

> 后端controller层处理

- `BookController`：在控制类中添加到修改书籍页面的跳转，以及接收到修改书籍的请求处理过程。

```java
// 跳转到修改书籍页面
@RequestMapping("/toUpdateBook")
public String toUpdatePaper(int id, Model model) {
    // 跳转到修改页面时需要显示该书籍的信息
    Books books = bookService.queryBookById(id);  // 查询该书籍信息，返回前端时显示在页面上
    model.addAttribute("aBook", books);  // 将查询到的书籍信息传递到前端
    return "updateBook";
}

// 修改书籍的请求
@RequestMapping("/updateBook")
public String updateBook(Books books) {
    System.out.println("updateBook=>"+books);
    // 在业务层修改
    bookService.updateBook(books);
    return "redirect:/book/allBook";  // 重定向，实现请求复用
}
```



## 5. 删除书籍业务

> 前端页面

- `allBook.jsp`：书籍展示页面。添加删除书籍的链接。

> 后端controller层处理

- `BookController`：在控制类中添加接收到修改书籍的请求处理过程。

```java
// 删除书籍的请求
@RequestMapping("/deleteBook/{bookId}")
public String deleteBook(@PathVariable("bookId") int id) {
    // 在业务层修改
    bookService.deleteBook(id);
    return "redirect:/book/allBook";  // 重定向，实现请求复用
}
```



## 6. 搜索书籍业务

> 前端页面

- `allBook.jsp`：书籍展示页面。添加搜索书籍的搜索框。在本页面显示搜索后的结果。

> dao层处理

- `BookMapper`：在接口中添加按名查询书籍的方法。

```java
// 按名称查询书籍
List<Books> queryBookByName(@Param("bookName") String bookName);
```

- `BookMapper.xml`：在配置文件中添加按名查询书籍的Sql语句。

```xml
<select id="queryBookByName" resultType="Books">
    select * from ssmbuild.books where bookName = #{bookName};
</select>
```

> service层处理

- `BookService`：在接口中添加按名查询书籍的方法。

```java
// 按名称查询书籍
List<Books> queryBookByName(String bookName);
```

- `BookServiceImp`：在实现中添加按名查询书籍的方法。

```java
public List<Books> queryBookByName(String bookName) {
    return bookMapper.queryBookByName(bookName);
}
```

> controller层处理

- `BookController`：在控制类中添加接收到查询书籍的请求处理过程。

```java
// 查询书籍的请求
@RequestMapping("/queryBook")
public String queryBook(String queryBookName, Model model) {
    // 在业务层修改
    List<Books> list = bookService.queryBookByName(queryBookName);
    // 如果灭有查询到结果，返回提示，并显示全部的书籍信息
    if (list.size() == 0) {
        list = bookService.queryAllBook();
        model.addAttribute("error","未查到");
    }
    model.addAttribute("list",list);
    System.out.println("queryBook=>"+list);

    return "allBook";
}
```

