package com.todo.api.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class SignInRequestDto {
    @Email
    @Schema(defaultValue = "test4@example.com")
    private String email;

    @Schema(defaultValue = "test1234")
    private String password;
}
