package com.example.tilproject.dto;

import com.example.tilproject.domain.NewPost;
import com.example.tilproject.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchResponse {
    private String title;
    private String summary;
    private String imageUrl;
    private String postLink;
    private User user;

    public SearchResponse(NewPost newpost) {
        this.title = newpost.getTitle();
        this.summary = newpost.getSummary();
        this.imageUrl = newpost.getImageUrl();
        this.postLink = newpost.getPostLink();
        this.user = newpost.getUser();
    }
}
