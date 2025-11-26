package com.todo.api.todo.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TodoListResponseDto {
    private Long id;
    private Long ownerId;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isDone;
}
