package com.kobe.signUpApp.service.admin;

import com.kobe.signUpApp.dto.admin.request.AdminCreateRequest;
import com.kobe.signUpApp.dto.admin.response.AdminResponse;
import com.kobe.signUpApp.repository.admin.AdminJdbcRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    // 이메일로 사용자 정보 조회 메서드
    public AdminResponse getAdminByEmail(String email) {
        return adminJdbcRepository.getAdminByEmail(email).orElse(null);
    }
}
