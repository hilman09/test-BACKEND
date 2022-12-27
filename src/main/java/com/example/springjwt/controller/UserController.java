package com.example.springjwt.controller;




import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.springjwt.dto.AuthenticationResponse;
import com.example.springjwt.dto.LoginRequest;
import com.example.springjwt.dto.RefreshTokenRequest;
import com.example.springjwt.dto.UserDto;
import com.example.springjwt.service.AuthService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("/api")
@RestController
public class UserController {

    private final AuthService authService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public String signUp(@RequestBody UserDto userDto){
        authService.signUp(userDto);
        return "Register Succesfully";
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
       
     }

     @PostMapping("/refreshtoken")
     @ResponseStatus(HttpStatus.OK)
     public AuthenticationResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return authService.refreshToken(refreshTokenRequest);
     }
}
