package com.todo.api.todo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.api.todo.dto.request.TodoCreateRequestDto;
import com.todo.api.todo.dto.request.TodoGroupCreateRequest;
import com.todo.api.todo.dto.response.TodoDetailResponseDto;
import com.todo.api.todo.service.TodoGroupService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/todo-group")
public class TodoGroupContoller {
    private final TodoGroupService todoGroupService;

    @PostMapping("")
    public Long createTodoGroup(
            @RequestAttribute Long userId,
            @Valid @RequestBody TodoGroupCreateRequest req) {

        Long groupId = todoGroupService.createTodoGroup(userId, req);
        return groupId;
    }
}
