package com.kobe.signUpApp.service.user;

import com.kobe.signUpApp.domain.user.User;
import com.kobe.signUpApp.dto.user.request.UserCreateRequest;
import com.kobe.signUpApp.dto.user.response.UserResponse;
import com.kobe.signUpApp.repository.user.UserJdbcRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserJdbcRepository userJdbcRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserJdbcRepository userJdbcRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userJdbcRepository = userJdbcRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(UserCreateRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getUserPassword());

        userJdbcRepository.saveUser(
                request.getUserName(),
                request.getUserEmail(),
                encodedPassword,
                request.getSamePassword()
                );
    }

    // 유저 인증 로직
    public UserResponse authenticateUser(String email, String rawPassword) {
        Optional<User> userOptional = userJdbcRepository.getUserWithPasswordByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(rawPassword, user.getUserPassword())) {
                // 비밀번호가 일치하면 UserResponse로 필요한 정보만 변환.
                return new UserResponse(user.getId(), user.getUserName(), user.getUserEmail(), "");
            } else {
                System.out.println("비밀번호 불일치");
            }
        } else {
            System.out.println("이메일 불일치: " + email);
        }
        return null; // 인증 실패 시 null 반환
    }

    // 이메일로 사용자 정보 조회 메서드
    public UserResponse getUserByEmail(String email) {
        return userJdbcRepository.getUserByEmail(email).orElse(null);
    }
}
