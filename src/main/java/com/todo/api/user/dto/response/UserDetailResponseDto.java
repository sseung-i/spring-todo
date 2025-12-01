package com.todo.api.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDetailResponseDto {
    private Long id;
    private String email;

}
