package com.example.tilproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Review extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewIdx;

    @Column(nullable = false)
    private String content;

    @JsonIgnoreProperties({"userId"})
    @JoinColumn(name = "userId")
    @ManyToOne
    private User user;

    @JsonIgnoreProperties({"reviews"})
    @JoinColumn(name = "board")
    @ManyToOne
    private Board board;
}
