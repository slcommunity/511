package com.example.tilproject.dto;

import com.example.tilproject.domain.UrlSection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UrlTurnDto {
    private String url;
    private String urlName;
    private UrlSection urlSection;
}
