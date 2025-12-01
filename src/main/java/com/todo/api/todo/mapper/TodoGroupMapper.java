package com.todo.api.todo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.todo.api.todo.dto.request.TodoGroupCreateRequest;
import com.todo.api.todo.dto.response.TodoGroupSimpleDetailResponseDto;
import com.todo.api.todo.dto.response.TodoGroupResponseDto;
import com.todo.api.user.dto.response.UserDetailResponseDto;

@Mapper
public interface TodoGroupMapper {

    void createTodoGroup(@Param("userId") Long userId, @Param("req") TodoGroupCreateRequest req);

    int editTodoGroup(@Param("userId") Long userId, @Param("groupId") Long groupId,
            @Param("req") Map<String, Object> req);

    List<TodoGroupSimpleDetailResponseDto> getTodoGroupList(@Param("userId") Long userId);

    List<UserDetailResponseDto> getTodoGroupMembers(@Param("groupId") Long groupId);

    TodoGroupResponseDto getTodoGroup(@Param("groupId") Long groupId);

    TodoGroupSimpleDetailResponseDto getTodoGroupDetail(@Param("groupId") Long groupId);

    void joinTodoGroup(@Param("userId") Long userId, @Param("groupId") Long groupId);

    boolean isGroupMember(@Param("groupId") Long groupId, @Param("userId") Long userId);

}
