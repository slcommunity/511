package com.example.tilproject.dto;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPagingRequestDto {
    private String userTurnInfo;
    private Integer curPage;
    private String searchWord;

    UserPagingRequestDto(String userTurnInfo, Integer curPage){
        this.userTurnInfo = userTurnInfo;
        this.curPage = curPage;
        this.searchWord = "default";
    }
}
