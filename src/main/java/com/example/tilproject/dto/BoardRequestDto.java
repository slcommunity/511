package com.example.tilproject.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardRequestDto {
    private String title;
    private String contents;
    private Long userid;
}