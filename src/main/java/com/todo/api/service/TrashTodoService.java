package com.todo.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.todo.api.dto.response.TrashTodoDetailResponseDto;
import com.todo.api.dto.response.TrashTodoListResponseDto;
import com.todo.api.mapper.TrashTodoMapper;

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