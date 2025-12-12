package com.todo.api.todo.dto.request;

import com.todo.api.todo.dto.response.TodoDetailResponseDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrashTodoCreateRequestDto {
    @Schema(hidden = true)
    private Long id;
    private Long todoId;

    public static TrashTodoCreateRequestDto fromTodo(TodoDetailResponseDto todo) {
        return TrashTodoCreateRequestDto.builder()
                .todoId(todo.getId())
                .build();
    }

}
