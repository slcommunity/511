package com.example.tilproject.service;

import com.example.tilproject.domain.Board;
import com.example.tilproject.domain.User;
import com.example.tilproject.dto.BoardResponseDto;
import com.example.tilproject.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public List<BoardResponseDto> searchBoards(User user){
        List<BoardResponseDto> boardResponseDtoList = new LinkedList<>();
        List<Board> boardList = boardRepository.findByUser(user);

        for (Board board:boardList) {
            BoardResponseDto boardResponseDto = new BoardResponseDto(board, user);
            boardResponseDtoList.add(boardResponseDto);
        }

        return boardResponseDtoList;
    }
}
