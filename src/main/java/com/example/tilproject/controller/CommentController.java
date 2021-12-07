package com.example.tilproject.controller;

import com.example.tilproject.domain.Comment;
import com.example.tilproject.domain.User;
import com.example.tilproject.dto.BoardCommentRequestDto;
import com.example.tilproject.dto.NewCommentRequestDto;
import com.example.tilproject.security.UserDetailsImpl;
import com.example.tilproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;
    private UserDetailsImpl userDetails;
//    private final UserDetailsServiceImpl userDetailsService;

    @PostMapping("/board/comment")
    public String setAritcleComment(@RequestBody NewCommentRequestDto newCommentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        commentService.setArticleComment(newCommentRequestDto, user);
        return "ok";
    }

    @GetMapping("/board/comment/{idx}")
    public List<Comment> getBoardComment(@PathVariable Long idx) {

        return commentService.getComments(idx);

    }

    @PutMapping("/boards/comment")
    public Long updateComment(@RequestBody BoardCommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return commentService.updateComment(requestDto, user).getIdx();
    }

    @DeleteMapping("/board/{boardId}/comment/{commentId}")
    public String deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        User user = userDetails.getUser();
        commentService.deleteComment(commentId);
        return "ok";
    }

}