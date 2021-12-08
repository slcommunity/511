package com.example.tilproject.controller;

import com.example.tilproject.domain.Board;
import com.example.tilproject.domain.User;
import com.example.tilproject.dto.BoardRequestDto;
import com.example.tilproject.dto.BoardResponseDto;
import com.example.tilproject.repository.BoardRepository;
import com.example.tilproject.security.UserDetailsImpl;
import com.example.tilproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;
//작성글 생성
    @PostMapping("/boards")
    public String createBoard(@RequestBody BoardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        boardService.SetBoard(requestDto, user);
        System.out.println(requestDto);
        return "ok";
    }

//작성글 조회
    @GetMapping("/board")
    public List<Board> readBoard() {
        List<Board> boards = boardService.getBoards();
        return boards;
    }


    @GetMapping("/board/{idx}")
    public Board getBorad(@PathVariable Long idx) {

        return boardService.getBoard(idx);
    }

//작성글 수정
    @PutMapping("/boards/{id}")
    public Long updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        System.out.println(user.getUsername());
        boardService.updateBoard(id, requestDto, user);
        return id;
    }


//작성글 삭제
    @DeleteMapping("/boards/{id}")
    public Long deleteBoard(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        boardService.deleteById(id);
        return id;
    }

    @GetMapping(value = "/my-boards")
    public List<BoardResponseDto> getMyBoards(@AuthenticationPrincipal UserDetailsImpl userDetails){
        User user = userDetails.getUser();
        return boardService.searchBoards(user);
    }
}
// 검색기능

