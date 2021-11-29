package com.example.tilproject.controller.admin;

import com.example.tilproject.dto.TurnDto;
import com.example.tilproject.dto.TurnModifyDto;
import com.example.tilproject.dto.TurnRequestDto;
import com.example.tilproject.service.adminService.AdminTurnService;
import com.example.tilproject.utils.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("admin/")
@RequiredArgsConstructor
public class AdminTurnController {

    private final AdminTurnService adminTurnService;

//    기수별 사용자 정보 불러오기, 페이징
//    @GetMapping("turn/{turn}")
//    public List<User> userListReturn(@PathVariable String turn){
//
//
//        return null;
//    }

    @GetMapping("turn")
    public Result getTurns(){
        List<TurnDto> turnDtos = adminTurnService.getTurn();

        return new Result(turnDtos);
    }

    @PostMapping("turn/{turnName}")
    public Long createTurn(@PathVariable String turnName){
        log.info("new turnName log = {}", turnName);
        TurnRequestDto turnRequestDto = new TurnRequestDto(turnName);
        return adminTurnService.createTurn(turnRequestDto);
    }

    @PutMapping("/turn")
    public Long updateTurn(@RequestBody TurnModifyDto turnModifyDto){

        return adminTurnService.modifyTurn(turnModifyDto);
    }

    @DeleteMapping("/turn/{turnName}")
    public String deleteTurn(@PathVariable String turnName){
        log.info("delete turn = {}", turnName);
        return adminTurnService.deleteTurn(turnName);
    }

}
