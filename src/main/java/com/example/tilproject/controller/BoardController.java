package com.example.tilproject.controller;

import com.example.tilproject.domain.Board;
import com.example.tilproject.repository.BoardRepository;
import com.example.tilproject.dto.BoardRequestDto;
import com.example.tilproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;

    @PostMapping("/boards")
    public Board createBoard (@RequestBody BoardRequestDto requestDto) {
        return boardService.SetBoard(requestDto);
    }

    @GetMapping("/boards")
    public List<Board> readBoard() {

        return boardService.getBoards();
    }


    @PutMapping("/boards/{id}")
    public Long updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto){
        boardService.updateBoard(id, requestDto);
        return id;
    }

    @DeleteMapping("/boards/{id}")
    public Long deleteBoard (@PathVariable Long id) {
        boardRepository.deleteById(id);
        return id;
    }

//

}