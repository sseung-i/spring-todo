package com.todo.todo.dto;

import java.time.LocalDate;

import com.todo.todo.entity.Todo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class CreateTodo {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class Request {
        @NotBlank
        @Size(max = 20, message = "title은 최대 20자까지 가능합니다.")
        private String title;
        @NotBlank
        @Size(max = 250, message = "content는 최대 250자까지 가능합니다.")
        private String content;
        private LocalDate startDate;
        private LocalDate endDate;
        private Boolean isDone;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class Response {
        private String title;
        private String content;
        private LocalDate startDate;
        private LocalDate endDate;
        private Boolean isDone;

        public static Response fromEntity(Todo todo) {
            return Response.builder()
                    .title(todo.getTitle())
                    .content(todo.getContent())
                    .startDate(todo.getStartDate())
                    .endDate(todo.getEndDate())
                    .isDone(todo.getIsDone())
                    .build();
        }
    }

}
