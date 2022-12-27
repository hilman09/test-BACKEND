package com.example.springjwt.service;

import java.time.Instant;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springjwt.dto.AuthenticationResponse;
import com.example.springjwt.dto.LoginRequest;
import com.example.springjwt.dto.RefreshTokenRequest;
import com.example.springjwt.dto.UserDto;
import com.example.springjwt.entities.User;
import com.example.springjwt.repository.UserRepository;
import com.example.springjwt.security.JwtProvider;

import lombok.AllArgsConstructor;

@Transactional
@AllArgsConstructor
@Service
public class AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;

    public void signUp(UserDto userDto){
        var user = User.builder()
                    .email(userDto.getEmail())
                    .username(userDto.getUsername())
                    .password(passwordEncoder.encode(userDto.getPassword()))
                    .build();
        
        userRepository.save(user);
    }

    public AuthenticationResponse login (LoginRequest loginRequest){
       Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
       SecurityContextHolder.getContext().setAuthentication(authentication);
       String token = jwtProvider.generateToken(authentication);
       return AuthenticationResponse.builder()
                .username(authentication.getName())
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expireAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .build();
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUsername());
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expireAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(refreshTokenRequest.getUsername())
                .build();
    }

}
