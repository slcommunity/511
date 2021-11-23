package com.example.tilproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {

    @GetMapping("/")
    public String getPageIndex(){
        return "/index";
    }

    @GetMapping("/admin")
    public String getPageAdmin(){
        return "/admin";
    }
}
