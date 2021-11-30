package com.example.tilproject.service;

import com.example.tilproject.domain.Board;
import com.example.tilproject.domain.Comment;
import com.example.tilproject.domain.User;
import com.example.tilproject.dto.BoardCommentRequestDto;
import com.example.tilproject.repository.BoardRepository;
import com.example.tilproject.dto.BoardRequestDto;
import com.example.tilproject.repository.CommentRepository;
import com.sun.xml.bind.v2.schemagen.xmlschema.NestedParticle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    public Board SetBoard(BoardRequestDto boardRequestDto, User user) {
        Board board = new Board(boardRequestDto, user);
        boardRepository.save(board);
        return board;
    }


    public Board getBoard(Long id) {
        return boardRepository.findById(id).orElseThrow(
                () ->new  NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
    }

    public List<Board> getBoards(){
        return boardRepository.findAllByOrderByModifiedAtDesc();
    }

    @Transactional
    public void setBoardcomment(BoardCommentRequestDto boardCommentRequestDto){
        Board board = boardRepository.findById(boardCommentRequestDto.getBoardId()).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다")
        );
        Comment comment = new Comment(boardCommentRequestDto,board);
        commentRepository.save(comment);
    }

    @Transactional
    public Board updateBoard(Long id, BoardRequestDto boardRequestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지않습니다.")
        );
        return board.update(boardRequestDto);
    }
}
