package com.example.tilproject.service.adminService;

import com.example.tilproject.domain.Turn;
import com.example.tilproject.domain.User;
import com.example.tilproject.dto.UserGetDto;
import com.example.tilproject.dto.UserPagingRequestDto;
import com.example.tilproject.repository.adminRepository.TurnRepository;
import com.example.tilproject.repository.UserRepository;
import com.example.tilproject.utils.PagingResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final UserRepository userRepository;
    private final TurnRepository turnRepository;
    private static final int BLOCK_PAGE_NUM_COUNT = 3;

    public PagingResult getUserList(UserPagingRequestDto userPagingRequestDto) {
        Turn turn = turnRepository.findByTurn(userPagingRequestDto.getUserTurnInfo());
        Pageable pageable = PageRequest.of(userPagingRequestDto.getCurPage()-1, BLOCK_PAGE_NUM_COUNT);
        Page<User> users = userRepository.findAllByTurn(turn, pageable);

        List<UserGetDto> userGetDtos = users.stream()
                .map(o -> new UserGetDto(o))
                .collect(Collectors.toList());

        return new PagingResult(userGetDtos, users.getTotalPages());
    }

    public PagingResult getUserSearch(String searchWord) {
        List<User> users = userRepository.findByNameContaining(searchWord);
        if(users == null) throw new IllegalArgumentException("해당하는 이름이 없습니다.");
        else{
            List<UserGetDto> userGetDto = users.stream()
                    .map(m -> new UserGetDto(m.getUsername(), m.getName(), m.getBlog(), m.getGithub()))
                    .collect(Collectors.toList());
            return new PagingResult(userGetDto, 1);
        }
    }


    public String deleteUser(String userId) {
        User user = userRepository.findByUsername(userId).get();
        userRepository.delete(user);
        return user.getName();
    }
}
