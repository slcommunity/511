package com.example.tilproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class NewPost extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String turn;

    private String blogUrl;

    @JsonIgnoreProperties({"recents"})
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    @ManyToOne
    private User user;

    public NewPost(String userid, String url, String title, String turn) {
        this.userId = userid;
        this.blogUrl = url;
        this.title = title;
        this.turn = turn;
    }



}
