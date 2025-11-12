package com.todo.todo.dto;

import java.time.LocalDateTime;

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
public class TrashTodoDto {
    private String title;
    private Long todoId;
    private LocalDateTime createdAt;

    public static TrashTodoDto fromEntity(Todo todo) {
        return TrashTodoDto.builder()
                .title(todo.getTitle())
                .todoId(todo.getId())
                .createdAt(todo.getCreatedAt())
                .build();
    }
}
