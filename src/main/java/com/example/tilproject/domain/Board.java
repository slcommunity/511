package com.example.tilproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Board extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardIdx;

    @Column(nullable = false)
    private String title;
    private String content;

    @JsonIgnoreProperties({"boards"})
    @JoinColumn(name = "userId")
    @ManyToOne
    private User user;


    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<Review> reviews;
}
