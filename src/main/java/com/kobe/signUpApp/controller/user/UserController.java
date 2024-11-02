package com.kobe.signUpApp.controller.user;

import com.kobe.signUpApp.dto.user.request.UserCreateRequest;
import com.kobe.signUpApp.dto.user.request.UserLoginRequest;
import com.kobe.signUpApp.dto.user.response.UserResponse;
import com.kobe.signUpApp.service.user.UserService;
import com.kobe.signUpApp.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody UserCreateRequest request) {
        userService.saveUser(request);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody UserLoginRequest request) {
        System.out.println("Received login request for email: " + request.getUserEmail());
        UserResponse user = userService.authenticateUser(request.getUserEmail(), request.getUserPassword());
        if (user != null) {
            String token = JwtUtil.generateToken(request.getUserEmail()); // jwtUtil 인스턴스로 메서드 호출
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("message", "Login successful");
            return ResponseEntity.ok(response); // JSON 형태로 토큰과 메시지 반환
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid credentials"));
        }
    }

    @GetMapping("/user-info")
    public ResponseEntity<UserResponse> getUserInfo(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String email = JwtUtil.validateTokenAndGetEmail(token);

        if (email != null) {
            UserResponse user = userService.getUserByEmail(email);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Map<String, String>> signIn(@RequestBody UserCreateRequest request) {
        UserResponse user = userService.authenticateUser(request.getUserEmail(), request.getUserPassword());
        if (user != null) {
            // 토큰 생성
            String token = JwtUtil.generateToken(user.getEmail());

            Map<String, String> response = new HashMap<>();
            response.put("email", user.getEmail());
            response.put("token", token);

            return ResponseEntity.ok(response); // JSON 형태로 email과 token 반환
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid credentials"));
        }
    }
}
