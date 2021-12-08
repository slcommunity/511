package com.example.tilproject.domain;

import com.example.tilproject.dto.BoardRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor

public class Board extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardIdx;

    @Column(nullable = false)
    private String title;
    private String content;

    //    @JsonBackReference
    @JsonIgnoreProperties({"boards"})
    @JoinColumn(name = "username")
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();


    public Board(BoardRequestDto boardRequestDto, User user) {
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
        this.boardIdx = boardRequestDto.getBoardIdx();
        this.user = user;
    }

    public Board(Long board) {
        this.boardIdx = board;
    }

    public Board update(BoardRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.user = user;
        return this;
    }
}

