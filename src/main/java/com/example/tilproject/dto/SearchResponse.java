package com.example.tilproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchResponse {
    private String title;
    private String summary;
    private String imageUrl;
    private String postLink;
    private String user;
}
