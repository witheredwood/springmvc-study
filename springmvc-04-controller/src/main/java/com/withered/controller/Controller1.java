package com.withered.controller;


import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 实现了 Controller 接口的类，这个类就是一个控制器
// 一个控制器只能实现一个类
public class Controller1 implements Controller {
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "Controller---------");
        mv.setViewName("test");
        return mv;
    }
}
