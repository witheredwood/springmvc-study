package com.withered.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller  // 会走视图解析器
@RequestMapping("/user")
public class LoginController {

    @RequestMapping("/main")
    public String main() {
        return "main";
    }

    @RequestMapping("/goLogin")
    public String login() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(HttpSession session, String username, String password, Model model) {
        // 把用户信息存在session中
        session.setAttribute("userLoginInfo", username);
        model.addAttribute("username", username);
        return "main";
    }

    @RequestMapping("/goOut")
    public String goOut(HttpSession session, String username, String password, Model model) {
        // 把用户信息存在session中
//        session.invalidate();  // 注销session
        session.removeAttribute("userLoginInfo");
        return "main";
    }

}
