package com.example.tilproject.service;

import com.example.tilproject.domain.Comment;
import com.example.tilproject.domain.User;
import com.example.tilproject.dto.CommentResponseDto;
import com.example.tilproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public List<CommentResponseDto> getMyComments(User user){
        List<CommentResponseDto> commentResponseDtoList = new LinkedList<>();
        List<Comment> commentList = commentRepository.findByUser(user);

        for (Comment comment : commentList) {

        }
        return commentResponseDtoList;
    }
}
