package com.example.tilproject.service.adminService;

import com.example.tilproject.domain.Url;
import com.example.tilproject.dto.UrlCreateDeleteDto;
import com.example.tilproject.dto.UrlModifyDto;
import com.example.tilproject.dto.UrlTurnDto;
import com.example.tilproject.repository.adminRepository.UrlRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminUrlService {

    private final UrlRepository urlRepository;

    public List<UrlTurnDto> getUrl(String turn) {
        List<Url> urls = urlRepository.findAllByUrlTurn(turn);
        if (urls == null) throw new IllegalArgumentException("해당하는 기수의 Url이 없습니다.");
        else {
            List<UrlTurnDto> urlTurnDtos = urls.stream()
                    .map(m -> new UrlTurnDto(m.getUrl(), m.getUrlName(), m.getRole()))
                    .collect(Collectors.toList());
            return urlTurnDtos;
        }
    }

    @Transactional
    public String modifyUrl(UrlModifyDto urlModifyDto) {
        Url url = urlRepository.findByUrlAndUrlNameAndUrlTurn(urlModifyDto.getUrl(),
                                                                urlModifyDto.getUrlname(),
                                                                urlModifyDto.getTurn()).get();

        url.setUrl(urlModifyDto.getTourl());
        url.setUrlName(urlModifyDto.getTourlname());
        return url.getUrlTurn();
    }

    @Transactional
    public String createUrl(UrlCreateDeleteDto urlCreateDeleteDto) {
        if (urlRepository.findByUrlAndUrlNameAndUrlTurn(urlCreateDeleteDto.getUrl(), urlCreateDeleteDto.getUrlName(), urlCreateDeleteDto.getTurn()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 Url입니다.");
        }
        Url url = new Url(urlCreateDeleteDto.getUrl(), urlCreateDeleteDto.getUrlName(), urlCreateDeleteDto.getTurn(), urlCreateDeleteDto.getRole());
        urlRepository.save(url);
        return url.getUrlName();
    }

    @Transactional
    public String deleteUrl(UrlCreateDeleteDto urlCreateDeleteDto) {
        if (!urlRepository.findByUrlAndUrlNameAndUrlTurn(urlCreateDeleteDto.getUrl(), urlCreateDeleteDto.getUrlName(), urlCreateDeleteDto.getTurn()).isPresent()) {
            throw new IllegalArgumentException("삭제할 Url이 없습니다..");
        }
        Url url = urlRepository.findByUrlAndUrlNameAndUrlTurn(urlCreateDeleteDto.getUrl(), urlCreateDeleteDto.getUrlName(), urlCreateDeleteDto.getTurn()).get();
        urlRepository.delete(url);
        return url.getUrlName();
    }
}
