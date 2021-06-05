package com.withered.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RestFulController {

    //   （传统方式） http://localhost:8080/add?a=1&b=2
    @RequestMapping("/add")
    public String test1(int a, int b, Model model) {
        int res = a + b;
        model.addAttribute("msg", "结果为" + res);
        return "test";
    }

    // RestFul 风格：http://localhost:8080/add/1/2
    // 支持缓存
//    @RequestMapping("/add/{a}/{b}")
//    public String test2(@PathVariable int a, @PathVariable int b, Model model) {
//        int res = a + b;
//        model.addAttribute("msg", "结果1为"+res);
//        return "test";
//    }

    @GetMapping("/add/{a}/{b}")
    public String test2(@PathVariable int a, @PathVariable int b, Model model) {

        model.addAttribute("msg", "结果1为：" + (a + b));
        return "test";
    }

    @PostMapping("/add/{a}/{b}")
    public String test3(@PathVariable int a, @PathVariable int b, Model model) {

        model.addAttribute("msg", "结果2为：" + (a + " " + b));
        return "test";
    }

}
