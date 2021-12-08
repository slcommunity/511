package com.example.tilproject.repository;

import com.example.tilproject.domain.Board;
import com.example.tilproject.domain.Comment;
import com.example.tilproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository <Comment,Long> {

    List<Comment> findByBoardOrderByCreatedAtDesc(Board board);

    List<Comment> findByUser(User user);
}

