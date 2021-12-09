package com.example.tilproject.dto;

import com.example.tilproject.domain.Turn;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TurnDto {
    private Long idx;
    private String turn;
    private List<UserGetDto> users;

    public TurnDto(Turn turn) {
        this.idx = turn.getIdx();
        this.turn = turn.getTurn();
        this.users = turn.getUsers().stream()
                .map(user -> new UserGetDto(user))
                .collect(Collectors.toList());
    }
}
