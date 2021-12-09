package com.example.tilproject.controller;

import com.example.tilproject.domain.Board;
import com.example.tilproject.domain.User;
import com.example.tilproject.dto.BoardRequestDto;
import com.example.tilproject.dto.BoardResponseDto;
import com.example.tilproject.security.UserDetailsImpl;
import com.example.tilproject.service.BoardService;
import com.example.tilproject.utils.PagingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class BoardController {

    private final BoardService boardService;

    //작성글 생성
    @PostMapping("/board")
    public String createBoard(@RequestBody BoardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        boardService.SetBoard(requestDto, user);
        System.out.println(requestDto);
        return "ok";
    }

    //작성글 조회
    @GetMapping("/boards/{curPage}")
    public PagingResult readBoard(@PathVariable Integer curPage) {
        return boardService.getBoards(curPage);
    }

    @GetMapping("/boards/title/{word}")
    public PagingResult searchBoard(@PathVariable String word) {
        return boardService.getSearchResult(word);
    }

    @GetMapping("/board/{idx}")
    public Board getBoard(@PathVariable Long idx) {
        return boardService.getBoard(idx);
    }

    //작성글 수정
    @PutMapping("/boards/{id}")
    public String updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return boardService.updateBoard(id, requestDto, user);
    }


    //작성글 삭제
    @DeleteMapping("/boards/{id}")
    public String deleteBoard(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return boardService.deleteById(id, user);
    }

    //작성글 검색
    @GetMapping(value = "/user/boards")
    public List<BoardResponseDto> getMyBoards(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return boardService.searchBoards(user);
    }
}