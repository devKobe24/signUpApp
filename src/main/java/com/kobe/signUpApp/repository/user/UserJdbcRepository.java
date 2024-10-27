package com.kobe.signUpApp.repository.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveUser(
            String userName,
            String userEmail,
            String userPassword,
            Boolean isSamePassword
    ) {
        String sql = "INSERT INTO user (user_name, user_email, user_password, password_match) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, userName, userEmail, userPassword, isSamePassword);
    }
}
