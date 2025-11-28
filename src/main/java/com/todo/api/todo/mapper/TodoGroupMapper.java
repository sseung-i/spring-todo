package com.todo.api.todo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.todo.api.todo.dto.request.TodoGroupCreateRequest;

@Mapper
public interface TodoGroupMapper {

    void createTodoGroup(@Param("userId") Long userId, @Param("req") TodoGroupCreateRequest req);

}
