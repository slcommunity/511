package com.example.tilproject.domain;

import com.example.tilproject.dto.BoardRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @JsonIgnoreProperties({"boards"})
    @JoinColumn(name = "userId")
    @ManyToOne
    private User user;


    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<Review> reviews;


    public Board(BoardRequestDto boardRequestDto) {
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContents();
    }

    public Board update(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContents();
        return this;
    }
}
