package com.example.tilproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TurnModifyDto {
    private String oldTurn;
    private String newTurn;
}
