package com.todo.api.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.api.user.config.JwtProvider;
import com.todo.api.user.constant.UserEnum;
import com.todo.api.user.dto.request.SignInRequestDto;
import com.todo.api.user.dto.request.SignUpRequestDto;
import com.todo.api.user.dto.request.UserAuthProviderInsertDto;
import com.todo.api.user.dto.response.UserAuthResponseDto;
import com.todo.api.user.dto.response.UserLoginResponseDto;
import com.todo.api.user.mapper.AuthMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtProvider jwtProvider;
    private final AuthMapper authMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(SignUpRequestDto req) {
        authMapper.createUser(req);

        Long userId = req.getId();
        String email = req.getEmail();
        String hashPw = passwordEncoder.encode(req.getPassword());

        UserAuthProviderInsertDto dto = new UserAuthProviderInsertDto();
        dto.setUserId(userId);
        dto.setProvider(UserEnum.UserProviderType.LOCAL);
        dto.setProviderUserId(email);
        dto.setPasswordHash(hashPw);

        authMapper.addAuthProvider(dto);

    }

    @Transactional
    public UserLoginResponseDto signIn(SignInRequestDto req) {
        UserAuthResponseDto user = authMapper.passwordFindByEmail(req.getEmail());

        if (user == null) {
            throw new RuntimeException("헤당 이메일의 사용자가 존재하지 않습니다.");
        }

        if (!passwordEncoder.matches(req.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        String token = jwtProvider.createToken(user.getId(), req.getEmail());

        return new UserLoginResponseDto(user.getId(), user.getEmail(), token);
    }
}
