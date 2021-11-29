package com.example.tilproject.controller;


import com.example.tilproject.domain.User;
import com.example.tilproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final UserService userService;

    @GetMapping(value = "/users")
    public List<User> getUsers(@RequestParam(required = false) String searchName){
        return userService.getUsers(searchName);
    }
}
