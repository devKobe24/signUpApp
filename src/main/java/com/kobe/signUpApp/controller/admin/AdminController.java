package com.kobe.signUpApp.controller.admin;

import com.kobe.signUpApp.dto.admin.request.AdminCreateRequest;
import com.kobe.signUpApp.dto.admin.request.AdminLoginRequest;
import com.kobe.signUpApp.dto.admin.response.AdminResponse;
import com.kobe.signUpApp.service.admin.AdminService;
import com.kobe.signUpApp.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    @PostMapping("/admin-login")
    public ResponseEntity<Map<String, String>> loginAdmin(@RequestBody AdminLoginRequest request) {
        System.out.println("Received login request for email: " + request.getAdminEmail());
        AdminResponse admin = adminService.authenticateAdmin(request.getAdminEmail(), request.getAdminNumber(), request.getConfirmPassword(), request.getAdminPassword());
        if (admin != null) {
            String token = JwtUtil.generateToken(request.getAdminEmail());
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("admin_email",request.getAdminEmail());
            response.put("admin_number",request.getAdminNumber());
            response.put("confirm_password",request.getConfirmPassword());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid credentials"));
        }
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