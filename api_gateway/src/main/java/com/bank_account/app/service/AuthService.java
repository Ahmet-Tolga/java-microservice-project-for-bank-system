package com.bank_account.app.service;


import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.bank_account.app.dto.request.RequestLoginDto;
import com.bank_account.app.dto.request.RequestRegisterDto;
import com.bank_account.app.dto.response.ResponseAuthDto;
import com.bank_account.app.entity.Role;
import com.bank_account.app.entity.User;
import com.bank_account.app.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;
    
    public ResponseAuthDto register(RequestRegisterDto requestRegisterDto){
        User new_user=User.builder().id(UUID.randomUUID().toString()).username(requestRegisterDto.getUsername()).email(requestRegisterDto.getEmail())
        .password(passwordEncoder.encode(requestRegisterDto.getPassword())).role(Role.USER).build();

        userRepository.save(new_user);

        String token=jwtService.generateToken(new_user);

        return ResponseAuthDto.builder().token(token).build();
    }

    public ResponseAuthDto login(RequestLoginDto requestLoginDto){
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(requestLoginDto.getEmail(),requestLoginDto.getPassword())
        );

        User user=userRepository.findByEmail(requestLoginDto.getEmail()).get();

        var token=jwtService.generateToken(user);

        return ResponseAuthDto.builder().token(token).build();

    }
    
}