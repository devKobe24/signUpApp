package com.kobe.signUpApp.controller.admin;

import com.kobe.signUpApp.dto.admin.request.AdminCreateRequest;
import com.kobe.signUpApp.dto.admin.response.AdminResponse;
import com.kobe.signUpApp.service.admin.AdminService;
import com.kobe.signUpApp.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/admin-register")
    public void registerAdmin(@RequestBody AdminCreateRequest request) {
        this.adminService.saveAdmin(request);
    }

    @GetMapping("/admin-info")
    public ResponseEntity<AdminResponse> getAdminInfo(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String email = JwtUtil.validateTokenAndGetEmail(token);

        if (email != null) {
            AdminResponse admin = adminService.getAdminByEmail(email);
            return ResponseEntity.ok(admin);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
