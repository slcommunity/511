package com.example.tilproject.domain;

import com.example.tilproject.dto.NewCommentRequestDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Comment extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long idx;

    @Column(nullable = false)
    private String content;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "board", nullable = false)
    private Board board;

    @JsonBackReference
    @JsonIgnoreProperties({"comments"})
    @JoinColumn(name = "username")
    @ManyToOne
    private User user;

    public Comment(NewCommentRequestDto requestDto, Board board, User user) {
        this.content = requestDto.getContent();
        this.board = board;
        this.user = user;

    }


}