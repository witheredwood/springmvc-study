package com.withered.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/c3")
public class Controller3 {

    //    @RequestMapping("/t1")
    @RequestMapping("/c3/t1")
    public String test(Model model) {
        model.addAttribute("msg", "Controller3.........");
        return "test";
    }


}
