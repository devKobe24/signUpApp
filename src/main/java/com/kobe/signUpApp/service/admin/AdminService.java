package com.kobe.signUpApp.service.admin;

import com.kobe.signUpApp.domain.admin.Admin;
import com.kobe.signUpApp.dto.admin.request.AdminCreateRequest;
import com.kobe.signUpApp.dto.admin.response.AdminResponse;
import com.kobe.signUpApp.repository.admin.AdminJdbcRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    private final AdminJdbcRepository adminJdbcRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminService(
            AdminJdbcRepository adminJdbcRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.adminJdbcRepository = adminJdbcRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveAdmin(AdminCreateRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getAdminPassword());

        adminJdbcRepository.saveAdmin(
                request.getAdminName(),
                request.getAdminNumber(),
                request.getAdminEmail(),
                encodedPassword,
                request.getIsSamePassword()
        );
    }

    // 유저 인증 로직
    public AdminResponse authenticateAdmin(String email, String number, String confirmPassword, String rawPassword) {
        Optional<Admin> adminOptional = adminJdbcRepository.getAdminWithPasswordByAdminNumber(number);
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            if (passwordEncoder.matches(rawPassword, admin.getAdminPassword())) {
                return new AdminResponse(admin.getId(), admin.getAdminName(), admin.getAdminEmail(), admin.getAdminNumber(), confirmPassword);
            } else {
                System.out.println("비밀번호 불일치");
            }
        } else {
            System.out.println("이메일 불일치: " + email);
        }
        return null;
    }

    // 이메일로 사용자 정보 조회 메서드
    public AdminResponse getAdminByEmail(String email) {
        return adminJdbcRepository.getAdminByEmail(email).orElse(null);
    }
}
