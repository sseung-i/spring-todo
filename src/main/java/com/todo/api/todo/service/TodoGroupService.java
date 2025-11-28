package com.todo.api.todo.service;

import org.springframework.stereotype.Service;

import com.todo.api.todo.dto.request.TodoGroupCreateRequest;
import com.todo.api.todo.dto.response.TodoGroupResponseDto;
import com.todo.api.todo.mapper.TodoGroupMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoGroupService {
    private final TodoGroupMapper todoGroupMapper;

    public Long createTodoGroup(Long userId, TodoGroupCreateRequest req) {
        todoGroupMapper.createTodoGroup(userId, req);

        Long groupId = req.getId();

        return groupId;
        // TodoGroupResponseDto 반환타입?
    }

}
