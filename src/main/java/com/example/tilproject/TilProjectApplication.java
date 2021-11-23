package com.example.tilproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TilProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TilProjectApplication.class, args);
    }

}
