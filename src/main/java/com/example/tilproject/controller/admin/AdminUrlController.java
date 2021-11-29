package com.example.tilproject.controller.admin;

import com.example.tilproject.dto.UrlTurnDto;
import com.example.tilproject.service.adminService.AdminUrlService;
import com.example.tilproject.utils.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("admin/")
@RequiredArgsConstructor
public class AdminUrlController {

    private final AdminUrlService adminUrlService;

    @GetMapping("url/{turn}")
    public Result getPresentation(@PathVariable String turn) {
        List<UrlTurnDto> urls = adminUrlService.getUrl(turn);
        log.info("finding urls matches with turn = {}", turn);
        return new Result(urls);
    }

}
