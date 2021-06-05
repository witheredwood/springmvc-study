package com.withered.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    @RequestMapping("/h1")
    public String hello(Model model) {
        // 封装数据
        model.addAttribute("msg", "hello, SpringMVC Annotation!!!!");
        return "hello";  // 会被视图解析器处理
    }

    @RequestMapping("/h2")
    public String hello2(Model model) {
        // 封装数据
        model.addAttribute("msg", "hello, 2, SpringMVC Annotation!!!!");
        return "hello";  // 会被视图解析器处理
    }

    @RequestMapping("/h3")
    public String hello3(Model model) {
        // 封装数据
        model.addAttribute("msg", "hello, 3, SpringMVC Annotation!!!!");
        return "hello";  // 会被视图解析器处理
    }
}
