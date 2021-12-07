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
@RequestMapping("admin/")
@RequiredArgsConstructor
public class AdminTurnController {

    private final AdminTurnService adminTurnService;

    @GetMapping("turn")
    public Result getTurns(){
        List<TurnsGetDto> turnDtos = adminTurnService.getTurn();
        return new Result(turnDtos);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("turn/{turnName}")
    public Long createTurn(@PathVariable String turnName){

        TurnRequestDto turnRequestDto = new TurnRequestDto(turnName);
        return adminTurnService.createTurn(turnRequestDto);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("turn")
    public Long updateTurn(@RequestBody TurnModifyDto turnModifyDto){

        return adminTurnService.modifyTurn(turnModifyDto);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("turn/{turnName}")
    public String deleteTurn(@PathVariable String turnName){
        log.info("delete turn = {}", turnName);
        return adminTurnService.deleteTurn(turnName);
    }

}
