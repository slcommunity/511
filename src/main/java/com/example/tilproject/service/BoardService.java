package com.example.tilproject.service;

import com.example.tilproject.domain.Board;
import com.example.tilproject.domain.User;
import com.example.tilproject.dto.BoardRequestDto;
import com.example.tilproject.dto.BoardResponseDto;
import com.example.tilproject.repository.BoardRepository;
import com.example.tilproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
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
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
    }

    public List<Board> getBoards() {
        List<Board> allByOrderByCreatedAtDesc = boardRepository.findAllByOrderByCreatedAtDesc();
        return allByOrderByCreatedAtDesc;
    }

    @Transactional
    public String updateBoard(Long id, BoardRequestDto boardRequestDto, User user) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지않습니다.")
        );

        if(user.getUsername().equals(board.getUser().getUsername())){
            board.update(boardRequestDto, user);
            return "success";
        }else{
            return "fail";
        }

    }

    public String deleteById(Long id, User user) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지않습니다.")
        );
        if(user.getUsername().equals(board.getUser().getUsername())){
            boardRepository.delete(board);
            return "success";
        }else{
            return "fail";
        }
    }

    public List<BoardResponseDto> searchBoards(User user) {
        List<BoardResponseDto> boardResponseDtoList = new LinkedList<>();
        List<Board> boardList = boardRepository.findByUser(user);

        for (Board board:boardList) {
            BoardResponseDto boardResponseDto = new BoardResponseDto(board, user);
            boardResponseDtoList.add(boardResponseDto);
        }
        return boardResponseDtoList;
    }
}