package com.kobe.signUpApp.dto.adminUser.request;

public class AdminUserCreateRequest {
    private String adminName;
    private String adminNumber;
    private String adminPassword;
    private String adminEmail;
    private String confirmAdminPassword;

    public String getAdminName() {
        return adminName;
    }

    public String getAdminNumber() {
        return adminNumber;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public String getConfirmAdminPassword() {
        return confirmAdminPassword;
    }
}
