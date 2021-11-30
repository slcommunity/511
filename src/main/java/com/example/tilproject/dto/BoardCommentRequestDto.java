package com.example.tilproject.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardCommentRequestDto {
    private Long boardId;
    private String content;
}
