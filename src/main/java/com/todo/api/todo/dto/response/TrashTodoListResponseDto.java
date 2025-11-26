package com.todo.api.todo.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrashTodoListResponseDto {
    private Long id;
    private Long todoId;
    private String title;
    private LocalDateTime createdAt;
}
