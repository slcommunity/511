package com.example.tilproject.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPagingRequestDto {
    private String userTurnInfo;
    private Integer curPage;
    private String searchWord;

}
