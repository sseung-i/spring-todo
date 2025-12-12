package com.todo.api.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.api.user.dto.response.UserDetailResponseDto;
import com.todo.api.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("")
    @Operation(summary = "회원정보 조회")
    public UserDetailResponseDto getUserDetail(@RequestAttribute("userId") Long userId) {
        return userService.getUserDetail(userId);
    }

}
