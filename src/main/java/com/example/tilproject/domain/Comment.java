package com.example.tilproject.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Comment extends Timestamped{
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
    
}
