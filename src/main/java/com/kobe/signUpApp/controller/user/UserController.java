package com.kobe.signUpApp.controller.user;

import com.kobe.signUpApp.dto.user.request.UserCreateRequest;
import com.kobe.signUpApp.service.user.UserService;
import org.springframework.web.bind.annotation.*;

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
}
