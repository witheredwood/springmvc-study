package com.withered.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// @Controller：该类会被spring接管。
// 被注解的类中所有的方法，返回值为String，并且具体的页面可以跳转，就会被视图解析器解析
@Controller
public class Controller2 {

    @RequestMapping("/t2")
    public String test2(Model model) {
        model.addAttribute("msg", "ControllerTest2....");
        return "test";  // 会被视图解析器解析 /WEB-INF/jsp/test.jsp
    }

    @RequestMapping("/t3")
    public String test3(Model model) {
        model.addAttribute("msg", "ControllerTest3....");
        return "test";  // 会被视图解析器解析 /WEB-INF/jsp/test.jsp
    }
}
