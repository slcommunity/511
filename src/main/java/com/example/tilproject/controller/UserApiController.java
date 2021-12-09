package com.example.tilproject.controller;

import com.example.tilproject.domain.User;
import com.example.tilproject.domain.UserRole;
import com.example.tilproject.dto.AdminResponse;
import com.example.tilproject.dto.JwtResponse;
import com.example.tilproject.dto.SignupRequestDto;
import com.example.tilproject.dto.UserDto;
import com.example.tilproject.security.UserDetailsImpl;
import com.example.tilproject.service.UserService;
import com.example.tilproject.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserApiController {

    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserService userService;


    //로그인
    @RequestMapping(value = "/user/sign-in", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserDto userDto) throws Exception {
        authenticate(userDto.getUsername(), userDto.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        User user = userService.searchUser(userDetails.getUsername());
        String role;
        if(user.getRole() == UserRole.ADMIN) {
            role = "admin";
        }
        else {
            role = "user";
        }
        return ResponseEntity.ok(new AdminResponse(token, userDetails.getUsername(), role));
    }

    //회원가입
    @PostMapping(value = "/user/sign-up")
    public ResponseEntity<?> createUser(@RequestBody SignupRequestDto userDto) throws Exception {
        userService.registerUser(userDto);
        authenticate(userDto.getUsername(), userDto.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername()));
    }

    //회원정보 수정
    @PutMapping(value = "/user")
    public String updateUser(@ModelAttribute SignupRequestDto userDto,
                             @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        User user = userDetails.getUser();
        System.out.println(user.getUsername());
        userService.updateUser(userDto, user);

        return "ok";
    }

    //회원 정보
    @GetMapping(value = "/user/{username}")
    public User getUserInfo(@PathVariable String username){
        return userService.searchUser(username);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    //ID 중복 확인
    @GetMapping(value = "/user/validation/username/{username}")
    public String getUser(@PathVariable String username){
        User user = userService.searchUser(username);
        if(user == null){
            return "ok";
        }else{
            return "no";
        }
    }
}