package com.example.tilproject.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Secured("ROLE_ADMIN")
    @GetMapping("/admin/test")
    public String test(){
        return "ok";
    }
}
