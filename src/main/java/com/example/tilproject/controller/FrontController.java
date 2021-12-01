package com.example.tilproject.controller;


import com.example.tilproject.domain.User;
import com.example.tilproject.service.TilCrawling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class FrontController {

    @Autowired
    TilCrawling tilCrawling;

    @GetMapping(value = "/")
    public String index() {
        return "/index";
    }

    @GetMapping(value = "/newpost")
    public String newpost() {
        return "/newpost";
    }

    @GetMapping(value = "/first")
    @ResponseBody
    public List<Map<String, Object>> first(String url) {
        List<Map<String, Object>> list = tilCrawling.crawling();
        return list;
    }

    @GetMapping(value = "/admin")
    public String getPageAdmin() {
        return "/admin";
    }


    @GetMapping(value = "/searchpost")
    public List<Map<String, Object>> getpost(@RequestParam(required = false) String search) {
        return tilCrawling.getpost(search);
    }
}
