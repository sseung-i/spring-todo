package com.todo.api.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.api.user.dto.request.SignInRequestDto;
import com.todo.api.user.dto.request.SignUpRequestDto;
import com.todo.api.user.dto.response.UserResponseDto;
import com.todo.api.user.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-up")
    public Boolean signUp(@Valid @RequestBody SignUpRequestDto req) {
        authService.signUp(req);

        return true;
    }

    @PostMapping("/sign-in")
    public UserResponseDto signIn(@Valid @RequestBody SignInRequestDto req) {

        return authService.signIn(req);
    }

}
