package com.todo.todo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.todo.todo.dto.CreateTodo;
import com.todo.todo.dto.TodoDto;
import com.todo.todo.entity.Todo;
import com.todo.todo.repository.TodoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    @Transactional
    public CreateTodo.Response createTodo(CreateTodo.Request request) {
        Todo todo = Todo.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .isDone(false)
                .build();

        todoRepository.save(todo);

        return CreateTodo.Response.fromEntity(todo);

    }

    public List<TodoDto> getTodos(LocalDate targetDate) {
        log.info("getTodos targetDate : " + targetDate);
        List<Todo> rows = (targetDate == null) ? todoRepository.findAll()
                : todoRepository.findTodosByDateBetween(targetDate);

        return rows.stream().map(TodoDto::fromEntity).toList();
    }

    // public List<TodoDto> deleteTodo(Integer todoId) {
    // // 삭제하면 휴지통으로 이동
    // }
}
