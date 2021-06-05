package com.withered.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class ModelTest {
    /*
     *  test11 和 test12 需要视图解析器。视图解析器在 /resouces/springmvc-servlet.xml 中配置
     * */
    // 需要视图解析器
    @RequestMapping("/m1/t11")
    public String test11(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        System.out.println(session.getId());

        // 转发
        return "test";  // 返回的视图在 /WEB-INF/jsp文件下
    }

    // 需要视图解析器
    @RequestMapping("/m1/t12")
    public String test12(Model model) {
        model.addAttribute("msg", "ModelTest 3 ....");
        // 重定向：地址栏会变化
        return "redirect:/index.jsp";  // 返回的视图在 /WEB-INF/jsp文件下
    }

    /*
     * 以下三种方式不需要视图解析器
     * */
    @RequestMapping("/m1/t1")
    public String test1(Model model) {
        model.addAttribute("msg", "ModelTest 1 ....");
        // 转发
        return "/WEB-INF/jsp/test.jsp";  // 返回的视图在 /WEB-INF/jsp文件下
    }

    @RequestMapping("/m1/t2")
    public String test2(Model model) {
        model.addAttribute("msg", "ModelTest 2 ....");
        // 转发
        return "forward:/WEB-INF/jsp/test.jsp";  // 返回的视图在 /WEB-INF/jsp文件下
    }

    @RequestMapping("/m1/t3")
    public String test3(Model model) {
        model.addAttribute("msg", "ModelTest 3 ....");
        // 重定向
        return "redirect:/index.jsp";  // 返回的视图在 /WEB-INF/jsp文件下
    }

}
