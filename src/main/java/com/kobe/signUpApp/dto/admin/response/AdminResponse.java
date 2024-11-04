package com.kobe.signUpApp.dto.admin.response;

import lombok.Data;

@Data
public class AdminResponse {
    private long id;
    private String name;
    private String email;
    private String adminNumber;
    private String confirmPassword;

    public AdminResponse(
            long id,
            String name,
            String email,
            String adminNumber,
            String confirmPassword
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.adminNumber = adminNumber;
        this.confirmPassword = confirmPassword;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAdminNumber() {
        return adminNumber;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
