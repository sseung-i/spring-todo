package com.todo.todo.service;

import org.springframework.stereotype.Service;

import com.todo.todo.dto.TodoDetailDto;
import com.todo.todo.entity.Todo;
import com.todo.todo.entity.TrashTodo;
import com.todo.todo.repository.TodoRepository;
import com.todo.todo.repository.TrashTodoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrashTodoService {
    private final TodoRepository todoRepository;
    private final TrashTodoRepository trashTodoRepository;

    public TodoDetailDto revertTodo(Long trashTodoId) {
        // trashTodo 꺼내온다.
        // todoId의 아이템의 isDeleted를 false로 바꾼다.
        // trashTodo에서 삭제한다.
        // set 해준 todo를 반환한다

        TrashTodo trashTodo = trashTodoRepository.findById(trashTodoId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 trashTodo가 존재하지 않습니다. id=" + trashTodoId));

        Long todoId = trashTodo.getTodoId();
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 todo가 존재하지 않습니다. id=" + todoId));

        todo.setIsDeleted(false);

        trashTodoRepository.deleteById(trashTodoId);

        return TodoDetailDto.fromEntity(todo);
    }
}
