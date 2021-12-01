package com.example.tilproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertNewPostDto {
    private String userId;
    private String title;
    private String turn;
    private String blogUrl;
}
