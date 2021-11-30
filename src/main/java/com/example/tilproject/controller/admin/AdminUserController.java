package com.example.tilproject.controller.admin;

import com.example.tilproject.dto.UserPagingRequestDto;
import com.example.tilproject.service.adminService.AdminUserService;
import com.example.tilproject.utils.PagingResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("admin/")
@RequiredArgsConstructor
public class AdminUserController {

    private final AdminUserService adminUserService;

    @GetMapping("user")
    public PagingResult getUsers(@ModelAttribute UserPagingRequestDto userPagingRequestDto) {
        log.info("{} {}", userPagingRequestDto.getCurPage(), userPagingRequestDto.getUserTurnInfo());

        PagingResult pagingResult = adminUserService.getUserList(userPagingRequestDto);

        return pagingResult;
    }


    @DeleteMapping("user/{userId}")
    public String deleteUser(@PathVariable String userId){
        return adminUserService.deleteUser(userId);
    }
}
