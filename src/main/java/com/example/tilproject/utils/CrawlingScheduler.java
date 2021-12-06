package com.example.tilproject.utils;


import com.example.tilproject.domain.NewPost;
import com.example.tilproject.domain.User;
import com.example.tilproject.repository.adminRepository.NewPostRepository;
import com.example.tilproject.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class CrawlingScheduler {
    private final UserRepository userRepository;
    private final NewPostRepository newPostRepository;


    @Scheduled(cron = "0 49 10 * * *")

    public void Crawling() throws IOException {

        List<User> users = userRepository.findAll();
        String blogUrl;
        String title;
        String summary;
        String postLink;
        String imageUrl;

        Elements card;

        //중복검사
        List<NewPost> titledata = newPostRepository.findAll();
        List<String> overlap = new ArrayList<>();
        for(int i=0; i<titledata.size(); i++){
            overlap.add(titledata.get(i).getTitle());
        }

        for (User user : users) {
            blogUrl = user.getBlog();


            if (blogUrl.contains("tistory")) {
                Connection conn = Jsoup.connect(blogUrl);
                Document html = conn.get();
                card = html.select(".article-content");
                for(int i=0; i<card.size(); i++){
                    title = html.select(".title").get(i).text();
                    summary = html.select(".summary").get(i).text();
                    postLink = html.select(".link-article").get(i*2).attr("href");
                    imageUrl = html.select(".img-thumbnail").get(i).attr("src");

                    if (!overlap.contains(title)){
                        NewPost newPost = new NewPost(title, summary, postLink, imageUrl, user);
                        newPostRepository.save(newPost);
                    }
                }
            }
            else if(blogUrl.contains("https://velog.io")){
                Connection conn = Jsoup.connect(blogUrl);
                Document html = conn.get();
                card = html.select("div#root div div div div div div a h2");
                for(int i=0; i<card.size(); i++){
                    title = card.get(i).text();
                    summary = card.get(i).parent().parent().select("p").text();
                    postLink = card.get(i).parent().parent().select("a div img").attr("src");
                    imageUrl ="https://velog.io" + card.get(i).parent().attr("href");
                   /* postLink = "https://velog.io" + card.get(i).parent().attr("href");
                    imageUrl = card.get(i).parent().parent().select("a div img").attr("src");*/

                    if (!overlap.contains(title)){
                        NewPost newPost = new NewPost(title, summary, postLink, imageUrl, user);
                        newPostRepository.save(newPost);
                    }

                }
            }
        }
    }
}
