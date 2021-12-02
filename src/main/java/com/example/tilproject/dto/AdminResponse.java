package com.example.tilproject.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AdminResponse{

    private final String token;
    private final String username;
    private final String role;
}
