package com.example.tilproject.service;

import com.example.tilproject.domain.Turn;
import com.example.tilproject.domain.User;
import com.example.tilproject.domain.UserRole;
import com.example.tilproject.dto.SignupRequestDto;
import com.example.tilproject.repository.adminRepository.TurnRepository;
import com.example.tilproject.repository.UserRepository;
import com.example.tilproject.utils.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";
    private final UserRepository userRepository;
    private final TurnRepository turnRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final S3Uploader s3Uploader;

    public User searchUser(String username){
        return userRepository.findByUsername(username).orElse(null);
    }

    public void registerUser(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        // 회원 ID 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }

        // 패스워드 인코딩
        String name = requestDto.getName();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String blog = requestDto.getBlog();
        String github = requestDto.getGithub();
        Turn turn = turnRepository.findByTurn(requestDto.getTurn());

//        String image = requestDto.getImage();
        String image = null;
        // 사용자 ROLE 확인
        UserRole role = UserRole.USER;
        if (requestDto.isAdmin()) {

            role = UserRole.ADMIN;
        }

        User user = new User(username, name, password, role, blog, github, turn, image);
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(SignupRequestDto requestDto, User user) throws IOException {
        User found = userRepository.findByUsername(user.getUsername()).orElse(null);
        String imageUrl = s3Uploader.upload(requestDto.getImage(), "userImages", user);

        String username = user.getUsername();
        String name = requestDto.getName();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String blog = requestDto.getBlog();
        String github = requestDto.getGithub();

        if(found != null){
            found.update( username,  name,  password, blog, github, imageUrl);
        }
    }

    public List<User> getUsers(String searchName){
        if(searchName == null){
            return userRepository.findAll();
        }
        else{
            return userRepository.findByNameContaining(searchName);
        }
    }
}
