package com.example.tilproject.dto;

import com.example.tilproject.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserGetDto {
    private String userName;
    private String name;
    private String blog;
    private String github;

    public UserGetDto(User user) {
        this.userName = user.getUsername();
        this.name = user.getName();
        this.blog = user.getBlog();
        this.github = user.getGithub();
    }
}
