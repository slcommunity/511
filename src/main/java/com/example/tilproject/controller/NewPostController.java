package com.example.tilproject.controller;

import com.example.tilproject.domain.NewPost;
import com.example.tilproject.dto.SearchRequestDto;
import com.example.tilproject.dto.SearchResponse;
import com.example.tilproject.service.NewpostService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NewPostController {

    private final NewpostService newpostService;

    //최신 블로그 글 가져오기
    @GetMapping(value = "/posts")
    @ResponseBody
    public List<NewPost> postList() {
        return newpostService.postList();
    }

    //검색
    @GetMapping(value = "/posts/{searchtxt}")
    @ResponseBody
    public List<SearchResponse> postSearch (SearchRequestDto txt) {
        return newpostService.postSearch(txt);
    }
}