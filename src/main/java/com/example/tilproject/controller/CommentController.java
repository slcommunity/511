package com.example.tilproject.controller;

import com.example.tilproject.domain.User;
import com.example.tilproject.dto.CommentResponseDto;
import com.example.tilproject.security.UserDetailsImpl;
import com.example.tilproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping(value = "/my-comments")
    public List<CommentResponseDto> getMyComments(@AuthenticationPrincipal UserDetailsImpl userDetails){
        User user = userDetails.getUser();
        return commentService.getMyComments(user);
    }
}
