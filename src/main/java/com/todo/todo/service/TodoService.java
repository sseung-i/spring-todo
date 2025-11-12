package com.todo.todo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.todo.todo.dto.CreateTodo;
import com.todo.todo.dto.TodoDetailDto;
import com.todo.todo.dto.TodoListItemDto;
import com.todo.todo.dto.TrashTodoDto;
import com.todo.todo.entity.Todo;
import com.todo.todo.entity.TrashTodo;
import com.todo.todo.repository.TodoRepository;
import com.todo.todo.repository.TrashTodoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final TrashTodoRepository trashTodoRepository;

    @Transactional
    public CreateTodo.Response createTodo(CreateTodo.Request request) {
        Todo todo = Todo.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .isDone(false)
                .isDeleted(false)
                .build();

        todoRepository.save(todo);

        return CreateTodo.Response.fromEntity(todo);

    }

    public List<TodoListItemDto> getTodos(LocalDate targetDate) {
        log.info("getTodos targetDate : " + targetDate);
        List<Todo> rows = (targetDate == null) ? todoRepository.findAll()
                : todoRepository.findTodosByDateBetween(targetDate);

        return rows.stream().map(TodoListItemDto::fromEntity).toList();
    }

    public TodoDetailDto getTodo(Long id) {

        // repository에서 반환타입은 Todo, getTodo가 반환하는건 TodoDetailDto
        // findById는 Optional<Todo>를 반환하기 때문에 후처리 필요
        return todoRepository.findById(id)
                .map(TodoDetailDto::fromEntity)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 Todo가 존재하지 않습니다. id=" + id));
    }

    @Transactional
    public TodoDetailDto deleteTodo(Long todoId) {

        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 Todo가 존재하지 않습니다. id=" + todoId));

        todo.setIsDeleted(true);

        TrashTodo trashTodo = TrashTodo.builder()
                .todoId(todoId)
                .title(todo.getTitle())
                .content(todo.getContent())
                .startDate(todo.getStartDate())
                .endDate(todo.getEndDate())
                .isDone(todo.getIsDone())
                .build();

        trashTodoRepository.save(trashTodo);

        return TodoDetailDto.fromEntity(todo);
    }

    @Transactional
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
