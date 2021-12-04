package com.example.tilproject.controller;

import com.example.tilproject.domain.User;
import com.example.tilproject.domain.UserRole;
import com.example.tilproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoleController {
    private final UserService userService;

    @PostMapping(value = "/roles")
    public String getUserRole(@RequestParam(value = "userId",required = true)String userId){
        String role;
        if(userId.equals("")){
            role = "user";
        }else{
            User user = userService.searchUser(userId);
            if(user == null){
                role = "user";
            }else {
                if (user.getRole() == UserRole.ADMIN)
                    role = "admin";
                else
                    role = "user";
            }
        }
        return role;
    }
}
