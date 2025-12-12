package com.todo.api.todo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.todo.api.todo.dto.request.TodoCreateRequestDto;
import com.todo.api.todo.dto.response.TodoDetailResponseDto;
import com.todo.api.todo.dto.response.TodoListResponseDto;
import com.todo.api.todo.dto.response.TrashTodoDetailResponseDto;
import com.todo.api.todo.service.TodoService;

import io.swagger.v3.oas.annotations.Operation;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/todo")
public class TodoController {
    private final TodoService todoService;

    @PostMapping("")
    @Operation(summary = "todo 생성")
    public TodoDetailResponseDto createTodo(
            @RequestAttribute Long userId,
            @Valid @RequestBody TodoCreateRequestDto req) {

        TodoDetailResponseDto todo = todoService.createTodo(userId, req);
        return todo;
    }

    @GetMapping("/list")
    @Operation(summary = "todo 리스트 조회")
    public List<TodoListResponseDto> getTodos(
            @RequestAttribute Long userId,
            @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return todoService.getTodos(userId, date);
    }

    @GetMapping("/{id}")
    @Operation(summary = "todo 조회")
    public TodoDetailResponseDto getTodoDetail(@PathVariable(value = "id") Long id) {
        return todoService.getTodoDetail(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "todo 수정")
    public TodoDetailResponseDto editTodo(@PathVariable(value = "id") Long id,
            @RequestBody Map<String, Object> req) {

        return todoService.editTodo(id, req);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "todo 삭제")
    public TrashTodoDetailResponseDto deleteTodo(@PathVariable(value = "id") Long id) {
        return todoService.deleteTodo(id);
    }

    @PatchMapping("/revert/{trashTodoId}")
    @Operation(summary = "임시삭제중인 todo 되돌리기")
    public TodoDetailResponseDto revertTodo(@PathVariable(value = "trashTodoId") Long trashTodoId) {
        return todoService.revertTodo(trashTodoId);
    }
}
