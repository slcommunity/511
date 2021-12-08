package com.example.tilproject.dto;

import lombok.Data;

@Data
public class BoardRequestDto {
    private Long boardIdx;
    private String title;
    private String content;
}