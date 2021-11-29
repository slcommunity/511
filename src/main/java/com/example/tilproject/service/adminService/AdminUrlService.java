package com.example.tilproject.service.adminService;

import com.example.tilproject.domain.Url;
import com.example.tilproject.dto.UrlTurnDto;
import com.example.tilproject.repository.adminRepository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminUrlService {

    private final UrlRepository urlRepository;

    public List<UrlTurnDto> getUrl(String turn) {
        List<Url> urls = urlRepository.findAllByUrlTurn(turn);
        if(urls == null) throw new IllegalArgumentException("해당하는 기수의 Url이 없습니다.");
        else{
            List<UrlTurnDto> urlTurnDtos = urls.stream()
                    .map(m -> new UrlTurnDto(m.getUrl(), m.getUrlName(), m.getRole()))
                    .collect(Collectors.toList());
            return urlTurnDtos;
        }
    }
}
