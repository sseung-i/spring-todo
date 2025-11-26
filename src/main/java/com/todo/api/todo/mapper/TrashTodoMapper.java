package com.todo.api.todo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.todo.api.todo.dto.response.TrashTodoDetailResponseDto;
import com.todo.api.todo.dto.response.TrashTodoListResponseDto;

@Mapper
public interface TrashTodoMapper {
    List<TrashTodoListResponseDto> getTrashTodos();

    TrashTodoDetailResponseDto getTrashTodoDetail(@Param("id") Long id);

    void deleteTrashTodo(@Param("id") Long id);
}
