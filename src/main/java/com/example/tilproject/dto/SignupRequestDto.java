package com.example.tilproject.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {
    private String username;
    private String name;
    private String password;
    private String blog;
    private String github;
    private String turn;
    private String image;
    private boolean admin = false;
}