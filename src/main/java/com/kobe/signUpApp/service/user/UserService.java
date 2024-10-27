package com.kobe.signUpApp.service.user;

import com.kobe.signUpApp.dto.user.request.UserCreateRequest;
import com.kobe.signUpApp.repository.user.UserJdbcRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserJdbcRepository userJdbcRepository;

    public UserService(UserJdbcRepository userJdbcRepository) {
        this.userJdbcRepository = userJdbcRepository;
    }

    public void saveUser(UserCreateRequest request) {
        userJdbcRepository.saveUser(
                request.getUserName(),
                request.getUserEmail(),
                request.getUserPassword(),
                request.getSamePassword()
                );
    }
}
