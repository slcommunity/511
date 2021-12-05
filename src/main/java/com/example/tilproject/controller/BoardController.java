package com.example.tilproject.controller;

import com.example.tilproject.domain.Board;
import com.example.tilproject.domain.User;
import com.example.tilproject.dto.BoardResponseDto;
import com.example.tilproject.security.UserDetailsImpl;
import com.example.tilproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping(value = "/my-boards")
    public List<BoardResponseDto> getMyBoards(@AuthenticationPrincipal UserDetailsImpl userDetails){
        User user = userDetails.getUser();
        return boardService.searchBoards(user);
    }
}
