package com.todo.todo.dto;

import java.time.LocalDate;

import com.todo.todo.entity.Todo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TodoListItemDto {
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isDone;

    public static TodoListItemDto fromEntity(Todo todo) {
        return TodoListItemDto.builder()
                .title(todo.getTitle())
                .startDate(todo.getStartDate())
                .endDate(todo.getEndDate())
                .isDone(todo.getIsDone())
                .build();
    }
}
