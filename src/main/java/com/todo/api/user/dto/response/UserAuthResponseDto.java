package com.todo.api.user.dto.response;

import lombok.Data;

@Data
public class UserAuthResponseDto {
    private Long id;
    private String email;
    private String encryptPassword;
}
