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
import java.util.List;

@AllArgsConstructor
@Component
public class CrawlingScheduler {
    private final UserRepository userRepository;
    private final NewPostRepository newPostRepository;

    @Scheduled(cron = "0 15 17 * * *")
    public void Crawling() throws IOException {

        List<User> users = userRepository.findAll();
        String blogUrl;
        String title;
        String summary;
        String postLink;
        String imageUrl;

        Elements card;
        for (User user : users) {
            blogUrl = user.getBlog();
            Connection conn = Jsoup.connect(blogUrl);
            Document html = conn.get();

            if (blogUrl.contains("tistory")) {
                card = html.select(".article-content");
                for(int i=0; i<card.size(); i++){
                    title = html.select(".title").get(i).text();
                    summary = html.select(".summary").get(i).text();
                    postLink = html.select(".link-article").get(i).attr("href");
                    imageUrl = html.select(".img-thumbnail").get(i).attr("src");

                    NewPost newPost = new NewPost(title, summary, postLink, imageUrl, user);
                    newPostRepository.save(newPost);
                }
            }
            else if(blogUrl.contains("https://velog.io")){
                card = html.select("div#root div div div div div div a h2");
                for(int i=0; i<card.size(); i++){
                    title = card.get(i).text();
                    summary = card.get(i).parent().parent().select("p").text();
                    postLink = "https://velog.io" + card.get(i).parent().attr("href");
                    imageUrl = card.get(i).parent().parent().select("a div img").attr("src");

                    NewPost newPost = new NewPost(title, summary, postLink, imageUrl, user);
                    newPostRepository.save(newPost);
                }
            }
            else{

            }
        }
    }
}
