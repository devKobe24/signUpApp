package com.kobe.signUpApp.dto.admin.request;

public class AdminLoginRequest {
    private String adminEmail;
    private String adminNumber;
    private String adminPassword;
    private String confirmPassword;

    public String getAdminEmail() {
        return adminEmail;
    }

    public String getAdminNumber() {
        return adminNumber;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public void setAdminNumber(String adminNumber) {
        this.adminNumber = adminNumber;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
