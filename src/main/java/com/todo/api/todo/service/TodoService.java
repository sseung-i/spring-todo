package com.todo.api.todo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.api.todo.dto.request.TodoCreateRequestDto;
import com.todo.api.todo.dto.request.TodoEditRequestDto;
import com.todo.api.todo.dto.request.TrashTodoCreateRequestDto;
import com.todo.api.todo.dto.response.TodoDetailResponseDto;
import com.todo.api.todo.dto.response.TodoListResponseDto;
import com.todo.api.todo.dto.response.TrashTodoDetailResponseDto;
import com.todo.api.todo.mapper.TodoMapper;
import com.todo.api.todo.mapper.TrashTodoMapper;
import com.todo.api.user.config.JwtProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoMapper todoMapper;
    private final TrashTodoMapper trashTodoMapper;
    private final JwtProvider jwtProvider;

    @Transactional
    public TodoDetailResponseDto createTodo(Long userId, TodoCreateRequestDto req) {

        todoMapper.createTodo(userId, req);
        return todoMapper.getTodoDetail(req.getId());

    }

    public List<TodoListResponseDto> getTodos(Long userId, LocalDate targetDate) {
        return todoMapper.getTodos(userId, targetDate != null ? targetDate : null);

    }

    public TodoDetailResponseDto getTodoDetail(Long id) {
        return todoMapper.getTodoDetail(id);
    }

    @Transactional
    public TodoDetailResponseDto editTodo(Long id, Map<String, Object> req) {
        TodoDetailResponseDto todo = todoMapper.getTodoDetail(id);
        if (todo.getIsDeleted())
            throw new Error("이미 삭제된 todo 입니다.");
        todoMapper.editTodo(id, req);

        return todoMapper.getTodoDetail(id);
    }

    @Transactional
    public TrashTodoDetailResponseDto deleteTodo(Long id) {
        todoMapper.deleteTodo(id);
        TodoDetailResponseDto todo = todoMapper.getTodoDetail(id);
        TrashTodoCreateRequestDto trashTodoReq = TrashTodoCreateRequestDto.fromTodo(todo);
        todoMapper.createTrashTodo(trashTodoReq);

        TrashTodoDetailResponseDto trashTodo = trashTodoMapper.getTrashTodoDetail(trashTodoReq.getId());

        return trashTodo;
    }

    @Transactional
    public TodoDetailResponseDto revertTodo(Long trashTodoId) {
        TrashTodoDetailResponseDto trashTodo = trashTodoMapper.getTrashTodoDetail(trashTodoId);
        Long todoId = trashTodo.getTodoId();
        todoMapper.revertTodo(todoId);
        TodoDetailResponseDto todo = todoMapper.getTodoDetail(todoId);

        trashTodoMapper.deleteTrashTodo(trashTodoId);

        return todo;

    }
}
