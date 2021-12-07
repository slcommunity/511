package com.example.tilproject.controller.admin;

import com.example.tilproject.dto.UserPagingRequestDto;
import com.example.tilproject.service.adminService.AdminUserService;
import com.example.tilproject.utils.PagingResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("admin/")
@RequiredArgsConstructor
public class AdminUserController {

    private final AdminUserService adminUserService;

    @Secured("ROLE_ADMIN")
    @GetMapping("user")
    public PagingResult getUsers(@ModelAttribute UserPagingRequestDto userPagingRequestDto) {
        if(!userPagingRequestDto.getSearchWord().equals("")) {
            return adminUserService.getUserSearch(userPagingRequestDto.getSearchWord());
        }else{
            PagingResult pagingResult = adminUserService.getUserList(userPagingRequestDto);
            return pagingResult;
        }
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("user/{userId}")
    public String deleteUser(@PathVariable String userId){
        return adminUserService.deleteUser(userId);
    }
}
