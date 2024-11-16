package com.kobe.signUpApp.service.user;

import com.kobe.signUpApp.domain.user.User;
import com.kobe.signUpApp.domain.user.UserRepository;
import com.kobe.signUpApp.domain.user.saveHistory.UserSaveHistory;
import com.kobe.signUpApp.domain.user.saveHistory.UserSaveHistoryRepository;
import com.kobe.signUpApp.dto.user.request.UserCreateRequest;
import com.kobe.signUpApp.dto.user.request.UserLoginRequest;
import com.kobe.signUpApp.dto.user.response.UserResponse;
import com.kobe.signUpApp.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 유저 회원 가입 기록 정보 확인을 위해 의존성 추가.
    private final UserSaveHistoryRepository userSaveHistoryRepository;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            UserSaveHistoryRepository userSaveHistoryRepository
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userSaveHistoryRepository = userSaveHistoryRepository;
    }

    // IP 주소 가져오는 메서드
    @Transactional
    public String getClientIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");

        // IP 주소가 여러 개인 경우 처리
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }

        // X-Forwarded-For 헤더에 여러 IP가 포함될 경우 첫 번째 IP를 가져옵니다.
        if (ipAddress != null && ipAddress.contains(",")) {
            ipAddress = ipAddress.split(",")[0].trim();
        }

        return ipAddress;
    }

    @Transactional
    public UserResponse saveUser(UserCreateRequest request, HttpServletRequest httpRequest) {
        // 이메일 중복 검사
        if (userRepository.getUserByUserEmail(request.getUserEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다: " + request.getUserEmail());
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(request.getUserPassword());

        // User 객체 생성
        User user = new User(request.getUserName(), request.getUserEmail(), encodedPassword);
        userRepository.save(user);

        // 회원 가입 기록 생성.
        String ipAddress = getClientIp(httpRequest);
        UserSaveHistory userSaveHistory = new UserSaveHistory(ipAddress, LocalDateTime.now());
        user.setUserSaveHistory(userSaveHistory); // 양방향 관계 설정.

        userSaveHistoryRepository.save(userSaveHistory);

        // 응답 생성
        return new UserResponse(user.getId(), user.getUserName(), user.getUserEmail());
    }

    // 유저 인증 로직
    public UserResponse authenticateUser(String email, String rawPassword) {
        Optional<User> userOptional = userRepository.getUserWithPasswordByUserEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(rawPassword, user.getUserPassword())) {
                // 비밀번호가 일치하면 UserResponse로 필요한 정보만 변환.
                return new UserResponse(user.getId(), user.getUserName(), user.getUserEmail());
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
        User user = userRepository.getUserByUserEmail(email).orElse(null);
        return new UserResponse(user.getId(), user.getUserName(), user.getUserEmail());
    }

    // 로그인 메서드
    @Transactional
    public ResponseEntity<Map<String, String>> loginUser(UserLoginRequest request, UserService userService) {
        UserResponse user = userService.authenticateUser(request.getUserEmail(), request.getUserPassword());

        if (user != null) {
            String token = JwtUtil.generateToken(request.getUserEmail()); // jwtUtil 인스턴스로 메서드 호출
            Map<String, String> response = new HashMap<>();
            System.out.println("=================> token:" + token);
            response.put("token", token);
            response.put("message", "Login successful");
            return ResponseEntity.ok(response); // JSON 형태로 토큰과 메시지 반환
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid credentials"));
        }
    }

    // 유저 정보 메서드
    @Transactional
    public ResponseEntity<UserResponse> userInfo(String authHeader, UserService userService) {
        String token = authHeader.replace("Bearer ", "");
        if (token.isEmpty()) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String email = JwtUtil.validateTokenAndGetEmail(token);
        if (email != null) {
            UserResponse user = userService.getUserByEmail(email);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // 로그인 메서드
    @Transactional
    public ResponseEntity<Map<String, String>> signIn(UserCreateRequest request, UserService userService) {
        UserResponse user = userService.authenticateUser(request.getUserEmail(), request.getUserPassword());
        if (user != null) {
            // 토큰 생성
            String token = JwtUtil.generateToken(user.getUserEmail());

            Map<String, String> response = new HashMap<>();
            response.put("email", user.getUserEmail());
            response.put("token", token);

            return ResponseEntity.ok(response); // JSON 형태로 email과 token 반환
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid credentials"));
        }
    }
}
