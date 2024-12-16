package com.bank_account.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank_account.app.dto.request.RequestLoginDto;
import com.bank_account.app.dto.request.RequestRegisterDto;
import com.bank_account.app.dto.response.ResponseAuthDto;
import com.bank_account.app.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    private ResponseEntity<ResponseAuthDto> register(@RequestBody RequestRegisterDto request){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    private ResponseEntity<ResponseAuthDto> login(@RequestBody RequestLoginDto request){
        return ResponseEntity.ok(authService.login(request));
    }
}