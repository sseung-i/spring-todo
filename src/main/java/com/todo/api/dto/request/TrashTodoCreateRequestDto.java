package com.todo.api.dto.request;

import com.todo.api.dto.response.TodoDetailResponseDto;

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
