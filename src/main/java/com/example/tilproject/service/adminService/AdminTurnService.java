package com.example.tilproject.service.adminService;

import com.example.tilproject.domain.Turn;
import com.example.tilproject.dto.TurnDto;
import com.example.tilproject.dto.TurnModifyDto;
import com.example.tilproject.dto.TurnRequestDto;
import com.example.tilproject.repository.adminRepository.TurnRepository;
import com.example.tilproject.repository.adminRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminTurnService {

    private final TurnRepository turnRepository;
    private final UserRepository userRepository;

    public List<TurnDto> getTurn(){
        List<Turn> turns = turnRepository.findAll();
        List<TurnDto> turnDtos = turns.stream()
                .map(m -> new TurnDto(m.getTurn()))
                .collect(Collectors.toList());
        return turnDtos;
    }

    @Transactional
    public Long createTurn(TurnRequestDto turnRequestDto) {

        if(turnRepository.findByTurn(turnRequestDto.getTurnName()) != null){
            throw new IllegalArgumentException("중복된 기수입니다.");
        }
        else{
            Turn turn = new Turn(turnRequestDto);
            turnRepository.save(turn);
            return turn.getIdx();
        }
    }

    @Transactional
    public Long modifyTurn(TurnModifyDto turnModifyDto){
        Turn turn = turnRepository.findByTurn(turnModifyDto.getOldTurn());
        if(turn == null){
            throw new IllegalArgumentException("존재하지 않는 기수입니다.");
        }
        else{
//            유저, url들의 기수 정보도 바꿔줘야 된다.
//            List<User> users = userRepository.findAllByUrlTurn(turnModifyDto.getOldTurn())
//            if(users != null){
//
//            }
            turn.setTurn(turnModifyDto.getNewTurn());
            return turn.getIdx();
        }
    }

    @Transactional
    public String deleteTurn(String turnName) {
        if(turnRepository.findByTurn(turnName) == null){
            throw new IllegalArgumentException("존재하지 않는 기수입니다.");
        }
        else{
            Turn turn = turnRepository.findByTurn(turnName);
            turnRepository.delete(turn);
            return turn.getTurn();
        }
    }


}
