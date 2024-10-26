package com.kobe.signUpApp.controller.user;

import com.kobe.signUpApp.domain.user.User;
import com.kobe.signUpApp.dto.user.request.UserCreateRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private final List<User> users = new ArrayList<>();

    @PostMapping("/register")
    public void registerUser(@RequestBody UserCreateRequest request) {
        users.add(new User(request.getUsername(), request.getEmail(), request.getPassword(), request.getConfirmPassword()));
    }
}
