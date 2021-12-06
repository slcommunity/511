package com.example.tilproject.service;

import com.example.tilproject.domain.NewPost;
import com.example.tilproject.dto.SearchRequestDto;
import com.example.tilproject.repository.adminRepository.NewPostRepository;
import com.example.tilproject.repository.adminRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NewpostService {

    @Autowired
    NewPostRepository newPostRepository;

    @Autowired
    UserRepository userRepository;

    public List<NewPost> postList () {
        return newPostRepository.findAll();
    }

    public List<Map<String, Object>> postSearch(SearchRequestDto txt) {
        List<Map<String, Object>> mergedList = new ArrayList<>();

        List<NewPost> titleResult = newPostRepository.findBytitleContaining(txt.getSearchtext());
        List<NewPost> summaryResult =  newPostRepository.findBysummaryContaining(txt.getSearchtext());

        if (txt.getSelecter().equals("title")){
            for(int i=0; i<titleResult.size(); i++){

                Map<String,Object> map = new HashMap<>();

                map.put("IMG", titleResult.get(i).getImageUrl());
                map.put("TITLE",titleResult.get(i).getTitle());
                map.put("SUMMARY", titleResult.get(i).getSummary());
                map.put("URL",titleResult.get(i).getPostLink());
                map.put("USER",titleResult.get(i).getUser());

                mergedList.add(map);
            }
        } else if (txt.getSelecter().equals("summary")){

            for(int i=0; i<summaryResult.size(); i++){

                Map<String,Object> map = new HashMap<>();

                map.put("IMG", summaryResult.get(i).getImageUrl());
                map.put("TITLE",summaryResult.get(i).getTitle());
                map.put("SUMMARY", summaryResult.get(i).getSummary());
                map.put("URL",summaryResult.get(i).getPostLink());
                map.put("USER",summaryResult.get(i).getUser());

                mergedList.add(map);
            }
        } else {

            for(int i=0; i<summaryResult.size(); i++){

                Map<String,Object> map = new HashMap<>();

                map.put("IMG", summaryResult.get(i).getImageUrl());
                map.put("TITLE",summaryResult.get(i).getTitle());
                map.put("SUMMARY", summaryResult.get(i).getSummary());
                map.put("URL",summaryResult.get(i).getPostLink());
                map.put("USER",summaryResult.get(i).getUser());

                mergedList.add(map);
            }

            for(int i=0; i<titleResult.size(); i++){

                Map<String,Object> map = new HashMap<>();

                map.put("IMG", titleResult.get(i).getImageUrl());
                map.put("TITLE",titleResult.get(i).getTitle());
                map.put("SUMMARY", titleResult.get(i).getSummary());
                map.put("URL",titleResult.get(i).getPostLink());
                map.put("USER",titleResult.get(i).getUser());

                mergedList.add(map);
            }
        }


        return mergedList;
    }
//    @Transactional
//    public List<Map<String,Object>> titlesearch (String key){
//        List<NewPost> result = newPostrepository.findBytitleContaining(key);
//        List<Map<String,Object>> resultsearch = new ArrayList<Map<String,Object>>();
//        for(int i=0;i< result.size();i++){
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("TITLE", result.get(i).getTitle());
//            map.put("SUMMARY", result.get(i).getSummary());
//            map.put("IMAGEURL", result.get(i).getImageUrl());
//            map.put("POSTLINK", result.get(i).getPostLink());
//            resultsearch.add(map);
//        }
//        System.out.println(resultsearch);
//
//
//
//        return resultsearch;
//    };
}
