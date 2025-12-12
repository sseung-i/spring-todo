package com.todo.api.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.api.user.dto.request.SignInRequestDto;
import com.todo.api.user.dto.request.SignUpRequestDto;
import com.todo.api.user.dto.response.UserLoginResponseDto;
import com.todo.api.user.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-up")
    @Operation(summary = "회원가입")
    public Boolean signUp(@Valid @RequestBody SignUpRequestDto req) {
        authService.signUp(req);

        return true;
    }

    @PostMapping("/sign-in")
    @Operation(summary = "로그인")
    public UserLoginResponseDto signIn(@Valid @RequestBody SignInRequestDto req) {

        return authService.signIn(req);
    }

}
