package com.todo.api.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.todo.api.todo.dto.response.TrashTodoDetailResponseDto;
import com.todo.api.todo.dto.response.TrashTodoListResponseDto;
import com.todo.api.todo.mapper.TrashTodoMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrashTodoService {
    private final TrashTodoMapper trashTodoMapper;

    public List<TrashTodoListResponseDto> getTrashTodos() {
        return trashTodoMapper.getTrashTodos();

    }

    public TrashTodoDetailResponseDto getTrashTodoDetail(Long id) {
        return trashTodoMapper.getTrashTodoDetail(id);
    }

    public Boolean deleteTrashTodo(Long id) {
        try {
            trashTodoMapper.deleteTrashTodo(id);
            return true;

        } catch (Exception e) {
            return false;
        }
    }
}