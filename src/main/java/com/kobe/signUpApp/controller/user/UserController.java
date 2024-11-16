package com.kobe.signUpApp.controller.user;

import com.kobe.signUpApp.dto.user.request.UserCreateRequest;
import com.kobe.signUpApp.dto.user.request.UserLoginRequest;
import com.kobe.signUpApp.dto.user.response.UserResponse;
import com.kobe.signUpApp.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponse registerUser(@RequestBody UserCreateRequest request, HttpServletRequest httpRequest) {
        return userService.saveUser(request, httpRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody UserLoginRequest request) {
        return userService.loginUser(request, userService);
    }

    @GetMapping("/user-info")
    public ResponseEntity<UserResponse> getUserInfo(@RequestHeader("Authorization") String authHeader) {
        return userService.userInfo(authHeader, userService);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Map<String, String>> signIn(@RequestBody UserCreateRequest request) {
        return userService.signIn(request, userService);
    }
}
