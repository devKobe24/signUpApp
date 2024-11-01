package com.kobe.signUpApp.service.user;

import com.kobe.signUpApp.dto.user.request.UserCreateRequest;
import com.kobe.signUpApp.dto.user.response.UserResponse;
import com.kobe.signUpApp.repository.user.UserJdbcRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserJdbcRepository userJdbcRepository;
    private final JdbcTemplate jdbcTemplate;

    public UserService(UserJdbcRepository userJdbcRepository, JdbcTemplate jdbcTemplate) {
        this.userJdbcRepository = userJdbcRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveUser(UserCreateRequest request) {
        userJdbcRepository.saveUser(
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                request.getSamePassword()
                );
    }

    // 유저 인증 로직
    public UserResponse authenticateUser(String email, String password) {
        Optional<UserResponse> userOptional = userJdbcRepository.getUserByEmail(email);
        if (userOptional.isPresent()) {
            UserResponse user = userOptional.get();
            if (user.getPassword().equals(password)) {
                System.out.println("로그인 성공: " + email); // 디버깅 로그
                return user;
            } else {
                System.out.println("비밀번호 불일치: " + email); // 디버깅 로그
            }
        } else {
            System.out.println("이메일 불일치: " + email); // 디버깅 로그
        }
        return null; // 인증 실패 시 null 반환.
    }

    // 이메일로 사용자 정보 조회 메서드
    public UserResponse getUserByEmail(String email) {
        return userJdbcRepository.getUserByEmail(email).orElse(null);
    }
}
