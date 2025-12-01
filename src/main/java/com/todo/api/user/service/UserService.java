package com.todo.api.user.service;

import org.springframework.stereotype.Service;

import com.todo.api.user.dto.response.UserDetailResponseDto;
import com.todo.api.user.dto.response.UserLoginResponseDto;
import com.todo.api.user.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public UserDetailResponseDto getUserDetail(Long id) {
        return userMapper.getUserDetail(id);
    }

}
