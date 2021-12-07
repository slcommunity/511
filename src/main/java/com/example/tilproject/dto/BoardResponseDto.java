package com.example.tilproject.dto;

import com.example.tilproject.domain.Board;
import com.example.tilproject.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@Getter
public class BoardResponseDto {
    private Long idx;
    private String title;
    private String content;
    private String createdAt;
    private String username;

    public BoardResponseDto(Board board, User user) {
        this.idx = board.getBoardIdx();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createdAt = board.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.username = user.getUsername();
    }
}
