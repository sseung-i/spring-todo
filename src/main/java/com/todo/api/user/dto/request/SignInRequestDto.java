package com.todo.api.user.dto.request;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class SignInRequestDto {
    @Email
    private String email;

    private String password;
}
