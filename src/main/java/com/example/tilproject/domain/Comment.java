package com.example.tilproject.domain;

import com.example.tilproject.dto.BoardCommentRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Getter
@Entity
@NoArgsConstructor
public class Comment extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long idx;

    @Column(nullable = false)
    private String content;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "board", nullable = false)
    private Board board;

    public Comment(BoardCommentRequestDto requestDto, Board board) {
        this.content = requestDto.getContent();
        this.board = board;
    }
}