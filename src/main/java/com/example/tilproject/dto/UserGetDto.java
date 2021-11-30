package com.example.tilproject.dto;

import com.example.tilproject.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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
