package com.kobe.signUpApp.repository.user;

import com.kobe.signUpApp.domain.user.User;
import com.kobe.signUpApp.dto.user.response.UserResponse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    // 전체 유저 정보 조회
    public List<UserResponse> getUsers() {
        String sql = "SELECT id, user_name, user_email FROM user";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String userName = rs.getString("user_name");
            String userEmail = rs.getString("user_email");
            return new UserResponse(id, userName, userEmail);
        });
    }

    // 이메일로 특정 유저 정보 조회
    public Optional<UserResponse> getUserByEmail(String email) {
        String sql = "SELECT * FROM user WHERE user_email = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{email}, (rs, rowNum) -> {
                long id = rs.getLong("id");
                String userName = rs.getString("user_name");
                String userEmail = rs.getString("user_email");
                return new UserResponse(id, userName, userEmail);
            }));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty(); // 결과가 없을 때 Optional.empty() 반환.
        }
    }

    // 이메일로 특정 유저 정보 조회(비밀번호 포함)
    public Optional<User> getUserWithPasswordByEmail(String email) {
        String sql = "SELECT user_name, user_email, user_password FROM user WHERE user_email = ?";

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{email}, (rs, rowNum) -> {
                String userName = rs.getString("user_name");
                String userEmail = rs.getString("user_email");
                String userPassword = rs.getString("user_password");
                return new User(userName, userEmail, userPassword);
            }));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}