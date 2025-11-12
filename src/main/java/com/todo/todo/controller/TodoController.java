package com.todo.todo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todo.todo.dto.CreateTodo;
import com.todo.todo.dto.TodoDto;
import com.todo.todo.service.TodoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
    public List<TodoDto> getTodos(@RequestParam(required = false) LocalDate date) {
        return todoService.getTodos(date);
    }

    @PostMapping("/todo")
    public CreateTodo.Response createTodo(@Valid @RequestBody CreateTodo.Request request) {
        return todoService.createTodo(request);
    }

}
