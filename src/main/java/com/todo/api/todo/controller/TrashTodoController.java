package com.todo.api.todo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.api.todo.dto.response.TrashTodoDetailResponseDto;
import com.todo.api.todo.dto.response.TrashTodoListResponseDto;
import com.todo.api.todo.service.TrashTodoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/todo/trash")
public class TrashTodoController {
    private final TrashTodoService trashTodoService;

    @GetMapping("/list")
    public List<TrashTodoListResponseDto> getTrashList() {
        return trashTodoService.getTrashTodos();
    }

    @GetMapping("/{id}")
    public TrashTodoDetailResponseDto getTrashTodoDetail(@PathVariable(value = "id") Long id) {
        return trashTodoService.getTrashTodoDetail(id);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteTrashTodo(@PathVariable(value = "id") Long id) {
        return trashTodoService.deleteTrashTodo(id);
    }

}
