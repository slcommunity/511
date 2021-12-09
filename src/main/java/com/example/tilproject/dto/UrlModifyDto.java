package com.example.tilproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UrlModifyDto {
    private String urlname;
    private String url;
    private String tourl;
    private String tourlname;
    private String turn;
}
