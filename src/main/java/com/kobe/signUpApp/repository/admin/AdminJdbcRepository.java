package com.kobe.signUpApp.repository.admin;

import com.kobe.signUpApp.dto.admin.response.AdminResponse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AdminJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public AdminJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveAdmin(
            String adminName,
            String adminNumber,
            String adminEmail,
            String adminPassword,
            Boolean isSamePassword
    ) {
        String sql = "INSERT INTO admin (admin_name, admin_number, admin_email, admin_password, password_match) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, adminName, adminNumber, adminEmail, adminPassword, isSamePassword);
    }

    // 이메일로 특정 Admin(관리자) 정보 조회
    public Optional<AdminResponse> getAdminByEmail(String email) {
        String sql = "SELECT * FROM admin WHERE admin_email = ?";

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{email}, (rs, rowNum) -> {
                long id = rs.getLong("id");
                String adminName = rs.getString("admin_name");
                String adminEmail = rs.getString("admin_email");
                String adminNumber = rs.getString("admin_number");
                Boolean confirmPassword = rs.getBoolean("password_match");
                return new AdminResponse(id, adminName, adminEmail, adminNumber, confirmPassword);
            }));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty(); // 결과가 없을 때 Optional.empty() 반환
        }
    }
}
