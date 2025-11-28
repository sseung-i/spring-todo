package com.todo.api.todo.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoGroupCreateRequest {
    private Long id;

    @NotNull(message = "name은 필수입니다.")
    @Size(max = 20)
    private String name;
    @NotNull(message = "description은 필수입니다.")
    @Size(max = 250)
    private String description;
}
