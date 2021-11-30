package com.example.tilproject.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "users")
public class User extends Timestamped{

    @Id
    @Column(length = 20)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @Column(nullable = false)
    private String name;


    @Column(nullable = false)
    private String blog;

    @Column(nullable = false)
    private String github;

    @Column(nullable = false)
    private String turn;

    @Column(nullable = true)
    private String image;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<NewPost> newPosts;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Board> boards;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Review> reviews;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private UserStatus userStatus;
  
    public User(String username, String name, String password, UserRole role, String blog, String github, String turn, String image) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
        this.blog = blog;
        this.github = github;
        this.turn = turn;
        this.image = image;
    }

    public void update(String username, String name, String password, String blog, String github, String image){
        this.username = username;
        this.password = password;
        this.name = name;
        this.blog = blog;
        this.github = github;
        this.image = image;
    }
}
