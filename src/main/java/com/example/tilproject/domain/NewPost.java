package com.example.tilproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class NewPost{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String blogUrl;

    private String github;

    @Column(nullable = false)
    private String turn;

    @JsonIgnoreProperties({"recents"})
    @JoinColumn(name = "userId")
    @ManyToOne
    private User user;
}
