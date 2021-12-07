package com.example.tilproject.service;

import com.example.tilproject.domain.NewPost;
import com.example.tilproject.dto.SearchRequestDto;
import com.example.tilproject.dto.SearchResponse;
import com.example.tilproject.repository.adminRepository.NewPostRepository;
import com.example.tilproject.repository.adminRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class NewpostService {

    @Autowired
    NewPostRepository newPostRepository;

    @Autowired
    UserRepository userRepository;

    public List<NewPost> postList() {
        return newPostRepository.findAll();
    }

    public List<SearchResponse> postSearch(SearchRequestDto txt) {

        List<NewPost> titleResult = newPostRepository.findBytitleContaining(txt.getSearchtext());
        List<NewPost> summaryResult = newPostRepository.findBysummaryContaining(txt.getSearchtext());

        List<SearchResponse> SearchResponseList = new LinkedList<>();

        if (txt.getSelecter().equals("title")) {
            for (NewPost newPost : titleResult) {
                SearchResponse searchResponse = new SearchResponse(newPost);
                SearchResponseList.add(searchResponse);
            }
        } else if (txt.getSelecter().equals("summary")) {

            for (NewPost newPost : summaryResult) {
                SearchResponse searchResponse = new SearchResponse(newPost);
                SearchResponseList.add(searchResponse);
            }
        } else {

            for (NewPost newPost : titleResult) {
                SearchResponse searchResponse = new SearchResponse(newPost);
                SearchResponseList.add(searchResponse);
            }

            for (NewPost newPost : summaryResult) {
                SearchResponse searchResponse = new SearchResponse(newPost);
                SearchResponseList.add(searchResponse);

            }
        }


        return SearchResponseList;
    }
}
