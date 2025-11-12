package com.todo.todo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todo.todo.dto.CreateTodo;
import com.todo.todo.dto.TodoDetailDto;
import com.todo.todo.dto.TodoListItemDto;
import com.todo.todo.dto.TodoRequestDto;
import com.todo.todo.service.TodoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/")
    public String ping() {

        return "pong";
    }

    @GetMapping("/todos")
    public List<TodoListItemDto> getTodos(@RequestParam(required = false) LocalDate date) {
        return todoService.getTodos(date);
    }

    @GetMapping("/todo/{todoId}")
    public TodoDetailDto getTodoDetail(@PathVariable Long todoId) {

        log.info("GET /todo/{} HTTP/1.1", todoId);

        return todoService.getTodo(todoId);
    };

    @PostMapping("/todo")
    public TodoDetailDto createTodo(@Valid @RequestBody TodoRequestDto.CreateTodo request) {
        return todoService.createTodo(request);
    }

    @PutMapping("/todo/{todoId}")
    public TodoDetailDto editTodo(@PathVariable Long todoId, @RequestBody TodoRequestDto.EditTodo request) {

        return todoService.editTodo(todoId, request);
    }

    @DeleteMapping("/todo/{todoId}")
    public TodoDetailDto deleteTodo(@PathVariable Long todoId) {

        return todoService.deleteTodo(todoId);
    }

    @PatchMapping("/todo/{trashTodoId}/revert")
    public TodoDetailDto revertTodo(@PathVariable Long trashTodoId) {

        return todoService.revertTodo(trashTodoId);
    }
}
