package com.example.tilproject.controller.admin;

import com.example.tilproject.dto.TurnDto;
import com.example.tilproject.dto.TurnModifyDto;
import com.example.tilproject.dto.TurnRequestDto;
import com.example.tilproject.security.UserDetailsImpl;
import com.example.tilproject.service.adminService.AdminTurnService;
import com.example.tilproject.utils.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("admin/")
@RequiredArgsConstructor
public class AdminTurnController {

    private final AdminTurnService adminTurnService;

    @Secured("ROLE_ADMIN")
    @GetMapping("turn")
    public Result getTurns(@AuthenticationPrincipal UserDetailsImpl userDetails){
        List<TurnDto> turnDtos = adminTurnService.getTurn();
        return new Result(turnDtos);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("turn/{turnName}")
    public Long createTurn(@PathVariable String turnName){
        log.info("new turnName log = {}", turnName);

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
