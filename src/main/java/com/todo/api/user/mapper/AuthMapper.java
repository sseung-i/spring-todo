package com.todo.api.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.todo.api.user.dto.request.SignUpRequestDto;
import com.todo.api.user.dto.request.UserAuthProviderInsertDto;
import com.todo.api.user.dto.response.UserAuthResponseDto;

@Mapper
public interface AuthMapper {

    void createUser(SignUpRequestDto req);

    void addAuthProvider(UserAuthProviderInsertDto req);

    UserAuthResponseDto passwordFindByEmail(@Param("email") String email);

    void addRefreshHash(@Param("id") Long id, @Param("hash") String hash);
}
