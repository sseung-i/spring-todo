package com.todo.api.todo.dto.request;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoCreateRequestDto {
    @Schema(hidden = true)
    private Long id;

    @NotNull(message = "title은 필수입니다.")
    @Size(max = 20)
    private String title;
    @NotNull(message = "content는 필수입니다.")
    @Size(max = 250)
    private String content;

    private LocalDate startDate;
    private LocalDate endDate;

    private Long groupId;
}
