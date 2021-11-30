package com.example.tilproject.domain;

import com.example.tilproject.dto.TurnRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Turn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false)
    private String turn;

    public Turn(TurnRequestDto requestDto){
        if (requestDto.getTurnName() == null || requestDto.getTurnName().isEmpty())
        {
            throw new IllegalArgumentException("저장할 턴 이름이 없습니다.");
        }
        this.turn = requestDto.getTurnName();
    }
}
