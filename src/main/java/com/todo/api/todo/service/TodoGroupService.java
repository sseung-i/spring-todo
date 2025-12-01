package com.todo.api.todo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.api.todo.dto.request.TodoGroupCreateRequest;
import com.todo.api.todo.dto.response.TodoGroupSimpleDetailResponseDto;
import com.todo.api.todo.dto.response.TodoGroupDetailResponseDto;
import com.todo.api.todo.dto.response.TodoGroupResponseDto;
import com.todo.api.todo.mapper.TodoGroupMapper;
import com.todo.api.user.dto.response.UserDetailResponseDto;
import com.todo.api.user.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoGroupService {
    private final TodoGroupMapper todoGroupMapper;
    private final UserMapper userMapper;

    public Long createTodoGroup(Long userId, TodoGroupCreateRequest req) {
        todoGroupMapper.createTodoGroup(userId, req);

        Long groupId = req.getId();

        return groupId;
        // TodoGroupResponseDto 반환타입?
    }

    @Transactional
    public TodoGroupResponseDto editTodoGroup(Long userId, Long groupId,
            Map<String, Object> req) {
        int updated = todoGroupMapper.editTodoGroup(userId, groupId, req);

        if (updated == 0) {
            throw new RuntimeException("그룹 정보 수정 권한이 없습니다.");
        }

        return todoGroupMapper.getTodoGroup(groupId);
    }

    public List<TodoGroupDetailResponseDto> getTodoGroupList(Long userId) {
        List<TodoGroupSimpleDetailResponseDto> groups = todoGroupMapper.getTodoGroupList(userId);

        List<TodoGroupDetailResponseDto> result = new ArrayList<>();

        for (TodoGroupSimpleDetailResponseDto g : groups) {
            TodoGroupDetailResponseDto dto = new TodoGroupDetailResponseDto();
            dto.setId(g.getId());
            dto.setName(g.getName());
            dto.setDescription(g.getDescription());

            // 2) owner 정보 조회
            UserDetailResponseDto owner = userMapper.getUserDetail(g.getOwnerId());
            dto.setOwner(owner);

            // 3) members 조회
            List<UserDetailResponseDto> members = todoGroupMapper.getTodoGroupMembers(g.getId());
            dto.setMembers(members);

            result.add(dto);
        }

        return result;
    }

    public TodoGroupDetailResponseDto getTodoGroupDetail(Long groupId) {
        TodoGroupSimpleDetailResponseDto detail = todoGroupMapper.getTodoGroupDetail(groupId);

        TodoGroupDetailResponseDto dto = new TodoGroupDetailResponseDto();
        dto.setId(detail.getId());
        dto.setName(detail.getName());
        dto.setDescription(detail.getDescription());
        UserDetailResponseDto owner = userMapper.getUserDetail(detail.getOwnerId());
        dto.setOwner(owner);

        List<UserDetailResponseDto> members = todoGroupMapper.getTodoGroupMembers(detail.getId());
        dto.setMembers(members);

        return dto;
    }

    @Transactional
    public TodoGroupDetailResponseDto joinTodoGroup(Long userId, Long groupId) {

        // (1) 그룹이 존재하는지 확인
        TodoGroupResponseDto group = todoGroupMapper.getTodoGroup(groupId);
        if (group == null)
            throw new RuntimeException("해당 그룹이 존재하지 않습니다.");

        // (2) 이미 가입한 멤버인지 확인
        boolean alreadyMember = todoGroupMapper.isGroupMember(groupId, userId);
        if (alreadyMember)
            throw new RuntimeException("이미 가입된 멤버입니다.");

        // (3) 가입 처리
        todoGroupMapper.joinTodoGroup(userId, groupId);

        return this.getTodoGroupDetail(groupId);
    }
}
