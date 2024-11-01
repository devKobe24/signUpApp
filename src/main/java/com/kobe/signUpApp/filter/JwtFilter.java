package com.kobe.signUpApp.filter;

import com.kobe.signUpApp.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();

        // 인증 없이 접근할 수 있는 경로에 대한 필터를 건너 뜁니다.
        if (path.startsWith("/v1/static") || path.equals("/v1/main.html") || path.equals("/api/register") || path.equals("/api/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                String email = JwtUtil.validateTokenAndGetEmail(token);
                if (email != null) {
                    request.setAttribute("email", email); // 요청 속성에 이메일 추가

                    // SecurityContext에 인증 정보 설정.
                    UserDetails userDetails = User.withUsername(email).password("").authorities(Collections.emptyList()).build();
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    logger.info("Token validation successful for email: " + email);
                } else {
                    logger.warn("Invalid token detected.");
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid Token");
                    return;
                }
            } catch (Exception e) {
                logger.error("Token validation error: " + e.getMessage());
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid Token");
                return;
            }
        } else {
            logger.warn("Authorization header is missing or dose not start with Bearer.");
        }
        filterChain.doFilter(request, response);
    }
}
