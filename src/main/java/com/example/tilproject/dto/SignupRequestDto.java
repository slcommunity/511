package com.example.tilproject.dto;

import com.example.tilproject.domain.Turn;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class SignupRequestDto {
    private String username;
    private String name;
    private String password;
    private String blog;
    private String github;
    private String turn;
    private MultipartFile image;
    private boolean admin = false;
}