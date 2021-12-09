package com.example.tilproject.controller.admin;

import com.example.tilproject.dto.TurnsGetDto;
import com.example.tilproject.dto.TurnModifyDto;
import com.example.tilproject.dto.TurnRequestDto;
import com.example.tilproject.service.adminService.AdminTurnService;
import com.example.tilproject.utils.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/admin/")
@RequiredArgsConstructor
public class AdminTurnController {

    private final AdminTurnService adminTurnService;

    //기수 조회
    @GetMapping("/turn")
    public Result getTurns(){
        List<TurnsGetDto> turnDtos = adminTurnService.getTurn();
        return new Result(turnDtos);
    }

    //기수 생성
    @Secured("ROLE_ADMIN")
    @PostMapping("/admin/turn/{turnName}")
    public Long createTurn(@PathVariable String turnName){

        TurnRequestDto turnRequestDto = new TurnRequestDto(turnName);
        return adminTurnService.createTurn(turnRequestDto);
    }

    //기수 수정
    @Secured("ROLE_ADMIN")
    @PutMapping("/admin/turn")
    public Long updateTurn(@RequestBody TurnModifyDto turnModifyDto){

        return adminTurnService.modifyTurn(turnModifyDto);
    }

    //기수 삭제
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/admin/turn/{turnName}")
    public String deleteTurn(@PathVariable String turnName){
        log.info("delete turn = {}", turnName);
        return adminTurnService.deleteTurn(turnName);
    }

}
