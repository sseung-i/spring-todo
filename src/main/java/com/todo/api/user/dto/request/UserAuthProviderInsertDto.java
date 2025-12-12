package com.todo.api.user.dto.request;

import com.todo.api.user.constant.UserEnum;

import lombok.Data;

@Data
public class UserAuthProviderInsertDto {
    private Long userId;
    private UserEnum.UserProviderType provider;
    private String providerUserId;
    private String encryptPassword;
}
