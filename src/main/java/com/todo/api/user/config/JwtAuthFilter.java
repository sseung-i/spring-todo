package com.todo.api.user.config;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;

    private static final AntPathMatcher pathMatcher = new AntPathMatcher();

    private final List<String> WHITE_LIST = List.of("/v1/sign-*",

            // swagger
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-ui/index.html",
            "/v3/api-docs/**");

    private boolean isWhitelist(String path) {
        return WHITE_LIST.stream().anyMatch(pattern -> pathMatcher.match(pattern, path));
    }

    private String resolveTokenFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }

        return authHeader.substring(7); // "Bearer " 이후 부분
    }

    @Override
    public void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();

        System.out.println("여기 => " + isWhitelist(path));

        // 1) 토큰 없이 접근 허용할 경로들 (회원가입/로그인 등)
        if (isWhitelist(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2) Authorization 헤더에서 토큰 꺼내기
        String token = resolveTokenFromRequest(request);
        if (token == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization 헤더가 없거나 형식이 잘못되었습니다.");
            return;
        }

        try {
            if (jwtProvider.isExpired(token)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "토큰 사용기간이 만료되었습니다.");
                return;
            }

            Long userId = jwtProvider.validateAndGetUserId(token);

            // 3) 이후 컨트롤러에서 꺼내 쓸 수 있도록 request에 넣어두기
            request.setAttribute("userId", userId);
            filterChain.doFilter(request, response);
        } catch (RuntimeException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "토큰이 유효하지 않습니다.");
        }
    }

}
