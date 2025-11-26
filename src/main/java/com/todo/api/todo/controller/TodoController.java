package com.todo.api.todo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.todo.api.todo.dto.request.TodoCreateRequestDto;
import com.todo.api.todo.dto.request.TodoEditRequestDto;
import com.todo.api.todo.dto.response.TodoDetailResponseDto;
import com.todo.api.todo.dto.response.TodoListResponseDto;
import com.todo.api.todo.dto.response.TrashTodoDetailResponseDto;
import com.todo.api.todo.service.TodoService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/todo")
public class TodoController {
    private final TodoService todoService;

    @PostMapping("")
    public TodoDetailResponseDto createTodo(@Valid @RequestBody TodoCreateRequestDto req) {

        TodoDetailResponseDto todo = todoService.createTodo(req);
        return todo;
    }

    @GetMapping("/list")
    public List<TodoListResponseDto> getTodos(
            @RequestParam(value = "date", required = false) LocalDate date) {
        return todoService.getTodos(date);
    }

    @GetMapping("/{id}")
    public TodoDetailResponseDto getTodoDetail(@PathVariable(value = "id") Long id) {
        return todoService.getTodoDetail(id);
    }

    @PutMapping("/{id}")
    public TodoDetailResponseDto editTodo(@PathVariable(value = "id") Long id,
            @RequestBody Map<String, Object> req) {

        return todoService.editTodo(id, req);
    }

    @DeleteMapping("/{id}")
    public TrashTodoDetailResponseDto deleteTodo(@PathVariable(value = "id") Long id) {
        return todoService.deleteTodo(id);
    }

    @PatchMapping("/revert/{trashTodoId}")
    public TodoDetailResponseDto revertTodo(@PathVariable(value = "trashTodoId") Long trashTodoId) {
        return todoService.revertTodo(trashTodoId);
    }
}
