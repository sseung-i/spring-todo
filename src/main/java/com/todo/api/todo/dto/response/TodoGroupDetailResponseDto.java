package com.todo.api.todo.dto.response;

import java.util.List;

import com.todo.api.user.dto.response.UserDetailResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TodoGroupDetailResponseDto {
    private Long id;
    private String name;
    private String description;
    private UserDetailResponseDto owner;
    private List<UserDetailResponseDto> members;
}
