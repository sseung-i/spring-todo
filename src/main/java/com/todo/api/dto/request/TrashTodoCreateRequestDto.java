package com.todo.api.dto.request;

import java.time.LocalDate;

import com.todo.api.dto.response.TodoDetailResponseDto;

import lombok.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrashTodoCreateRequestDto {
    private Long id;
    private Long todoId;

    @NotNull(message = "title은 필수입니다.")
    @Size(max = 20)
    private String title;
    @NotNull(message = "content는 필수입니다.")
    @Size(max = 250)
    private String content;

    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isDone;

    public static TrashTodoCreateRequestDto fromTodo(TodoDetailResponseDto todo) {
        return TrashTodoCreateRequestDto.builder()
                .todoId(todo.getId())
                .title(todo.getTitle())
                .content(todo.getContent())
                .startDate(todo.getStartDate())
                .endDate(todo.getEndDate())
                .isDone(todo.getIsDone())
                .build();
    }

}
