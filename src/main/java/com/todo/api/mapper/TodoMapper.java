package com.todo.api.mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.todo.api.dto.request.TodoCreateRequestDto;
import com.todo.api.dto.request.TrashTodoCreateRequestDto;
import com.todo.api.dto.response.TodoDetailResponseDto;
import com.todo.api.dto.response.TodoListResponseDto;

@Mapper
public interface TodoMapper {

    void createTodo(TodoCreateRequestDto req);

    List<TodoListResponseDto> getTodos(@Param("targetDate") LocalDate targetDate);

    TodoDetailResponseDto getTodoDetail(@Param("id") Long id);

    void editTodo(@Param("id") Long id, @Param("req") Map<String, Object> req);

    void deleteTodo(@Param("id") Long id);

    void createTrashTodo(TrashTodoCreateRequestDto req);

    void revertTodo(@Param("id") Long id);

    void deleteTrashTodo(@Param("id") Long id);

}
