package com.example.tilproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewCommentRequestDto {
    private Long boardIdx;
    private String content;
}