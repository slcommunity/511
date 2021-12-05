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
@RestController
public class UserApiController {

    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserService userService;


    @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
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

    @PostMapping(value = "/signup")
    public ResponseEntity<?> createUser(@RequestBody SignupRequestDto userDto) throws Exception {
        userService.registerUser(userDto);
        authenticate(userDto.getUsername(), userDto.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername()));
    }

    @PutMapping(value = "/userInfo")
    public String updateUser(@ModelAttribute SignupRequestDto userDto,
                             @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        User user = userDetails.getUser();
        System.out.println(user.getUsername());
        userService.updateUser(userDto, user);

        return "ok";
    }

    @GetMapping(value = "/userInfo/{username}")
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

    @GetMapping(value = "/user/check/{username}")
    public String getUser(@PathVariable String username){
        User user = userService.searchUser(username);
        if(user == null){
            return "ok";
        }else{
            return "no";
        }
    }
}