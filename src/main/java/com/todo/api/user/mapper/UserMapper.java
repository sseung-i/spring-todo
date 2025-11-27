package com.todo.api.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.todo.api.user.dto.response.UserResponseDto;

@Mapper
public interface UserMapper {
    UserResponseDto getUserDetail(@Param("id") Long id);
}
