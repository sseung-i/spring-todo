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
public class TodoDetailDto {
    private String title;
    private String content;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isDone;

    public static TodoDetailDto fromEntity(Todo todo) {

        return TodoDetailDto.builder()
                .title(todo.getTitle())
                .content(todo.getContent())
                .startDate(todo.getStartDate())
                .endDate(todo.getEndDate())
                .isDone(todo.getIsDone())
                .build();
    }

}
