package com.withered.controller;

import com.withered.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RequestMapping("/user")
public class UserController {

    // 请求地址：http://localhost:8080/user/t1?username=withered
    @GetMapping("/user/t1")
    public String test1(@RequestParam("username") String name, Model model) {
        // 接收前端参数
        System.out.println("接收到的前端参数为：" + name);
        // 将返回的结果传递给前端
        model.addAttribute("msg", name);
        // 视图跳转
        return "test";
    }

    // 前端接收到的时一个对象：User(id, name, age)
    /*
     * 接收前端用户传递的参数，判断参数的名字，假设名字直接在方法上，可以直接使用
     * 假设传递的时一个对象User，匹配User对象中的字段名。如果名字一致则成功，否则，匹配失败
     * 请求地址（参数顺序可以改变）：
     *   http://localhost:8080/user/t2?id=2&name=withered&age=10
     *   http://localhost:8080/user/t2?name=withered&age=10&id=2
     * */
    @GetMapping("/user/t2")
    public String test2(User user) {
        System.out.println("接收到的前端参数为：" + user);
        // 视图跳转
        return "test";
    }

    /*
     * Model、ModelMap、ModelAndView的区别
     * 1. Model：只适合用于存储数据
     * 2. ModelMap：继承了LinkedMap，有自身的方法，和继承了LinkedMap的方法
     * 3. ModelAndView：存储数据的同时，设置返回的逻辑视图，控制展示层的跳转
     * */
    @GetMapping("/user/t3")
    public String test3(ModelMap modelMap) {
        // 接收前端参数
        System.out.println("接收到的前端参数为：" + modelMap);
        // 将返回的结果传递给前端
        modelMap.addAttribute("msg", modelMap);
        // 视图跳转
        return "test";
    }
}
