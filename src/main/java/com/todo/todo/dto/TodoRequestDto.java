package com.todo.todo.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class TodoRequestDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class CreateTodo {
        @NotBlank
        @Size(max = 20)
        private String title;
        @NotBlank
        @Size(max = 250)
        private String content;

        private LocalDate startDate;
        private LocalDate endDate;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class EditTodo {
        @Size(max = 20)
        private String title;
        @Size(max = 250)
        private String content;

        private LocalDate startDate;
        private LocalDate endDate;

    }

}
