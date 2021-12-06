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

    @Column(nullable = true, length = 1000)
    private String summary;

    @Column(nullable = true)
    private String imageUrl;

    @Column(nullable = false)
    private String postLink;

    @JsonIgnoreProperties({"newPosts"})
    @JoinColumn(name = "username")
    @ManyToOne
    private User user;

    public NewPost(String title, String summary, String imageUrl, String postLink, User user) {
        this.title = title;
        this.summary = summary;
        this.imageUrl = imageUrl;
        this.postLink = postLink;
        this.user = user;
    }
}
