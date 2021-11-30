package com.example.tilproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserGetDto {
    private String userId;
    private String name;
    private String blog;
    private String github;
}
