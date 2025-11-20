package com.todo.api.dto.request;

import java.time.LocalDate;

import lombok.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoCreateRequestDto {
    private Long id;
    @NotNull(message = "title은 필수입니다.")
    @Size(max = 20)
    private String title;
    @NotNull(message = "content는 필수입니다.")
    @Size(max = 250)
    private String content;

    private LocalDate startDate;
    private LocalDate endDate;

}
