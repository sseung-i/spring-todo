package com.todo.api.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrashTodoDetailResponseDto {
    private Long id;
    private Long todoId;
    private String title;
    private String content;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isDone;
    private LocalDateTime createdAt;
}
