package com.example.tilproject.dto;

import com.example.tilproject.domain.Board;
import com.example.tilproject.domain.Comment;
import com.example.tilproject.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@Getter
public class CommentResponseDto {
    private Long idx;
    private String content;
    private Long boardIdx;
    private String boardTitle;
    private String username;
    private String createdAt;

    public CommentResponseDto(Comment comment, Board board, User user) {
        this.idx = comment.getIdx();
        this.content = comment.getContent();
        this.boardIdx = board.getBoardIdx();
        this.boardTitle = board.getTitle();
        this.username = user.getUsername();
        this.createdAt = comment.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
