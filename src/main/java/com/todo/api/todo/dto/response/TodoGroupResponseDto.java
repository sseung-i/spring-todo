package com.todo.api.todo.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TodoGroupResponseDto {
    private Long id;
    private String name;
    private String description;
    private Long owner_id; // 오너 테이블 정보 가져오기
    private List<Long> members; // 멤버 테이블 정보 가져오기
}
