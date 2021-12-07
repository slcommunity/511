package com.example.tilproject.repository;

import com.example.tilproject.domain.Comment;
import com.example.tilproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByUser(User user);
}
