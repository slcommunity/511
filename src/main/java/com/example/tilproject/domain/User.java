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
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;
    private String name;
    private String github;
    private String turn;
    private String image;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<NewPost> newPosts;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Board> boards;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Review> reviews;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private UserStatus userStatus;
}
