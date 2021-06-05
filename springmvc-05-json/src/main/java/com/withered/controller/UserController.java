package com.withered.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.withered.pojo.User;
import com.withered.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Controller  // 该注解，会被视图解析器处理
@RestController  // 该注解，使得该类下的所有方法都返回字符串
public class UserController {

    // json字符串要设置编码，防止乱码问题
//    @RequestMapping(value = "/j1", produces = "application/json;charset=utf-8")  // 这种方法不需要再配置xml文件
    @RequestMapping("/j1")  // 这种方法需要在springmvc-servlet.xml中配置
    @ResponseBody  // 不会走视图解析器，会直接返回一个字符串
    public String json1() throws JsonProcessingException {
        // jackson ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        //创建一个对象
        User user = new User("withered1", 10, "女");

        return mapper.writeValueAsString(user);
    }

    @RequestMapping("/j2")
    public String json2() throws JsonProcessingException {
        // jackson ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        List<User> userList = new ArrayList<User>() {{
            add(new User("withered1", 10, "女"));
            add(new User("withered2", 10, "女"));
            add(new User("withered3", 10, "女"));
            add(new User("withered4", 10, "女"));
            add(new User("withered5", 10, "女"));
        }};
        return mapper.writeValueAsString(userList);
    }

    @RequestMapping("/j3")
    public String json3() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        // 不适用时间戳的方式显示日期
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 自定义日期显示格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mapper.setDateFormat(sdf);

        Date date = new Date();

        // ObjectMapper， 时间解析后的默认格式是时间戳 Timestamp
        return mapper.writeValueAsString(date);
    }

    // 使用自己编写的工具类 JsonUtils 格式化日期
    @RequestMapping("/j4")
    public String json4() throws JsonProcessingException {
        Date date = new Date();
        String str = JsonUtils.getJson(date, "yyyy-MM-dd HH:mm:ss");
        return str;
    }

}
