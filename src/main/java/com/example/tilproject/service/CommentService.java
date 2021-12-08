package com.example.tilproject.service;

import com.example.tilproject.domain.Board;
import com.example.tilproject.domain.Comment;
import com.example.tilproject.domain.User;
import com.example.tilproject.dto.BoardCommentRequestDto;
import com.example.tilproject.dto.CommentResponseDto;
import com.example.tilproject.dto.NewCommentRequestDto;
import com.example.tilproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public void setArticleComment(NewCommentRequestDto newCommentRequestDto, User user) {
        System.out.println(newCommentRequestDto.getBoardIdx());
        Board board = new Board(newCommentRequestDto.getBoardIdx());
        Comment comment = new Comment(newCommentRequestDto, board, user);

        commentRepository.save(comment);
    }

    public List<CommentResponseDto> getComments(Long idx) {
        List<CommentResponseDto> commentResponseDtoList = new LinkedList<>();
        Board board = new Board(idx);
        List<Comment> commentList = commentRepository.findByBoardOrderByCreatedAtDesc(board);
        for (Comment comment : commentList) {
            CommentResponseDto commentResponseDto = new CommentResponseDto(comment, board, comment.getUser());
            commentResponseDtoList.add(commentResponseDto);
        }
        return commentResponseDtoList;
    }

    @Transactional
    public String updateComment(BoardCommentRequestDto boardCommentRequestDto, User user) {
        Comment comment = commentRepository.findById(boardCommentRequestDto.getCommentIdx()).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지않습니다.")
        );
        if(user.getUsername().equals(comment.getUser().getUsername())){
            comment.setContent(boardCommentRequestDto.getContent());
            return "success";
        }
        else{
            return "fail";
        }
    }

    @Transactional
    public String deleteComment(Long commentId, User user){
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지않습니다.")
        );
        if(user.getUsername().equals(comment.getUser().getUsername())){
            commentRepository.deleteById(commentId);
            return "success";
        }
        else{
            return "fail";
        }

    }

    @Transactional
    public List<CommentResponseDto> getMyComments(User user) {
        List<CommentResponseDto> commentResponseDtoList = new LinkedList<>();
        List<Comment> commentList = commentRepository.findByUser(user);
        if (!commentList.isEmpty()) {
            for (Comment comment : commentList) {
                Board board = comment.getBoard();
                CommentResponseDto commentResponseDto = new CommentResponseDto(comment, board, user);
                commentResponseDtoList.add(commentResponseDto);
            }
        }
        return commentResponseDtoList;
    }
}