package com.withered.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 放行：判断什么情况下登录了
        // 登录页面会放行：可以进入登录页面
        if (request.getRequestURI().contains("Login")) {
            return true;
        }
        //  第一次登录是没有session的，但是也要放行
        if (request.getRequestURI().contains("login")) {
            return true;
        }
        // session中有用户信息：说明用户已经登录过了
        HttpSession session = request.getSession();
        if (session.getAttribute("userLoginInfo") != null) {
            return true;
        }
        // 什么情况下没有登录：跳转到首页
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        return false;
    }
}
