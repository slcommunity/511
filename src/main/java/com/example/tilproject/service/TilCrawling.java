package com.example.tilproject.service;

import com.example.tilproject.domain.NewPost;
import com.example.tilproject.domain.User;
import com.example.tilproject.dto.InsertNewPostDto;
import com.example.tilproject.repository.NewPostRepository;
import com.example.tilproject.repository.UserRepository;
import lombok.Setter;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Setter
public class TilCrawling {

    @Autowired
    NewPostRepository newPostRepository;

    @Autowired
    UserRepository userRepository;

    InsertNewPostDto newPostDto = new InsertNewPostDto();

    @Transactional
    public List<Map<String, Object>> crawling() {

        List<Map<String,Object>> Cardlist = new ArrayList<Map<String,Object>>();

        String userid = "";
        String url = "";
        String title = "";
        String turn = "";


        try {
            List<NewPost> Postlist = newPostRepository.findAll();
            List<String> postString = new ArrayList<>();

            for (int i=0; i<Postlist.size(); i++) {
                postString.add(Postlist.get(i).getTitle());
            }

            List<User> userdata = userRepository.findAll();

            for (int i=0;i<userdata.size(); i++){
                User user = userdata.get(i);
                String Blogurl = user.getBlog();
                Connection conn = Jsoup.connect(Blogurl);

                Document html = conn.get();
                Elements Card;

                if (Blogurl.contains("tistory")) {
                    Card = html.select(".title");

                    for(int z=0; z<Card.size(); z++){
                        if(postString.contains(Card.get(z).text())){
                        } else {
                            newPostDto.setTitle(Card.get(z).text());
                            newPostDto.setBlogUrl(user.getBlog());
                            newPostDto.setUserId(user.getUsername());
                            newPostDto.setTurn(user.getTurn());

                            userid = newPostDto.getUserId();
                            url = newPostDto.getBlogUrl();
                            title = newPostDto.getTitle();
                            turn = newPostDto.getTurn();

                            NewPost newpost = new NewPost(userid, url, title, turn);

                            newPostRepository.save(newpost);
                        }
                    }
                } else {
                    Card = html.select("div#root div div div div div div a h2");

                    for(int z=0; z<Card.size(); z++){
                        if(postString.contains(Card.get(z).text())){
                        } else {
                            newPostDto.setTitle(Card.get(z).text());
                            newPostDto.setBlogUrl(Card.get(z).parent().attr("href"));
                            newPostDto.setUserId(user.getUsername());
                            newPostDto.setTurn(user.getTurn());

                            userid = newPostDto.getUserId();
                            url = newPostDto.getBlogUrl();
                            title = newPostDto.getTitle();
                            turn = newPostDto.getTurn();

                            NewPost newpost = new NewPost(userid, url, title, turn);

                            newPostRepository.save(newpost);
                        }
                    }
                }
            }
            for (int i=0; i<Postlist.size(); i++){
                Map<String,Object> map = new HashMap<String, Object>();
                map.put("TITLE", Postlist.get(i).getTitle());
                map.put("USERID", Postlist.get(i).getUserId());
                map.put("URL", Postlist.get(i).getBlogUrl());
                map.put("IDX", Postlist.get(i).getIdx());
                Cardlist.add(map);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;
        return Cardlist;
    };

    public List<Map<String, Object>> getpost(String search) {
        List<Map<String,Object>> postresult = new ArrayList<Map<String,Object>>();

        List<NewPost> searchlist = newPostRepository.findAll();


        for (int i=0; i< searchlist.size(); i++){
            Map<String, Object> map = new HashMap<String, Object>();

            map.put("TITLE", searchlist.get(i).getTitle());
            map.put("URL", searchlist.get(i).getBlogUrl());
            map.put("USERID", searchlist.get(i).getUserId());
            postresult.add(map);
        }

        System.out.println(postresult);

        return postresult;

    };
};
