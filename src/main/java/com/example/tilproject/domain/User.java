package com.example.tilproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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


    @Column(nullable = true)
    private String image;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idx")
    private Turn turn;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<NewPost> newPosts;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Board> boards;

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Comment> comments;

//    OneToOne에서 Lazy로딩이 안먹혀서 꺼놨습니다.
//    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
//    private UserStatus userStatus;


    public User(String username, String name, String password, UserRole role, String blog, String github, Turn turn, String image) {
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
