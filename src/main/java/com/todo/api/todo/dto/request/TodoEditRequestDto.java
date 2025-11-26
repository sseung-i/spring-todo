package com.todo.api.todo.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoEditRequestDto {
    @Size(max = 20)
    private String title;
    @Size(max = 250)
    private String content;

    private LocalDate startDate;
    private LocalDate endDate;
}
