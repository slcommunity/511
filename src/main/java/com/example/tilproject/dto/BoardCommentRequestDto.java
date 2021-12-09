package com.example.tilproject.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardCommentRequestDto {
    private Long boardIdx;
    private Long commentIdx;
    private String content;
}