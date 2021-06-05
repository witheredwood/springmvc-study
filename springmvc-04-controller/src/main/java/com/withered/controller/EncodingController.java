package com.withered.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/*
 * 解决乱码问题
 * */
@Controller
public class EncodingController {

    //    @RequestMapping("/e/t1")
    @RequestMapping("/e/t1")
    public String test1(String name, Model model, HttpServletRequest request) {
        // 使用过滤器解决乱码问题
        System.out.println("name=" + name);
        // 获取表单提交的值
        model.addAttribute("msg", name);
        // 视图跳转
        return "test";
    }
}
