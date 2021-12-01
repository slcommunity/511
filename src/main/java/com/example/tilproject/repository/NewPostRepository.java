package com.example.tilproject.repository;

import com.example.tilproject.domain.NewPost;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NewPostRepository extends JpaRepository<NewPost, String> {


}
