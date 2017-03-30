package com.scorpion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Scorpion on 2017/3/20.
 */
@Controller
@RequestMapping("/welcome")
public class HelloController {
    @RequestMapping("hello")
    public String print(ModelMap model){
        model.addAttribute("message","hello world");
        return "hello";
    }

}
