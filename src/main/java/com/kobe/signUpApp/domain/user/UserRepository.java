package com.kobe.signUpApp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserWithPasswordByUserEmail(String userEmail);
    Optional<User> getUserByUserEmail(String userEmail);
    Optional<User> getUserByUserEmailAndUserPassword(String userEmail, String userPassword);
}
