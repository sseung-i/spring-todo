package com.todo.api.todo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.api.todo.dto.request.TodoGroupCreateRequest;
import com.todo.api.todo.dto.response.TodoGroupDetailResponseDto;
import com.todo.api.todo.dto.response.TodoGroupResponseDto;
import com.todo.api.todo.service.TodoGroupService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/todo-group")
public class TodoGroupContoller {
    private final TodoGroupService todoGroupService;

    @GetMapping("/list")
    public List<TodoGroupDetailResponseDto> getTodoGroupList(@RequestAttribute Long userId) {
        return todoGroupService.getTodoGroupList(userId);
    }

    @GetMapping("/{id}")
    public TodoGroupDetailResponseDto getTodoGroupDetail(@RequestAttribute Long userId,
            @PathVariable Long id) {
        return todoGroupService.getTodoGroupDetail(id);
    }

    @PostMapping("")
    public Long createTodoGroup(
            @RequestAttribute Long userId,
            @Valid @RequestBody TodoGroupCreateRequest req) {

        Long groupId = todoGroupService.createTodoGroup(userId, req);
        return groupId;
    }

    @PutMapping("/{id}")
    public TodoGroupResponseDto modifyTodoGroup(
            @RequestAttribute Long userId,
            @PathVariable Long id,
            @RequestBody Map<String, Object> req) {
        return todoGroupService.editTodoGroup(userId, id, req);

    }

    @PostMapping("/{id}/join")
    public TodoGroupDetailResponseDto joinTodoGroup(@RequestAttribute Long userId, @PathVariable Long id) {
        return todoGroupService.joinTodoGroup(userId, id);

    }

}
