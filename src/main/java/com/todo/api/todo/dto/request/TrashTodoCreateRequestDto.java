package com.todo.api.todo.dto.request;

import com.todo.api.todo.dto.response.TodoDetailResponseDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrashTodoCreateRequestDto {
    private Long id;
    private Long todoId;

    public static TrashTodoCreateRequestDto fromTodo(TodoDetailResponseDto todo) {
        return TrashTodoCreateRequestDto.builder()
                .todoId(todo.getId())
                .build();
    }

}
