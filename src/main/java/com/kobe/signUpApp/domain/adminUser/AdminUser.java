package com.kobe.signUpApp.domain.adminUser;

public class AdminUser {
    private String adminName;
    private String adminNumber;
    private String adminPassword;
    private String adminEmail;
    private String confirmAdminPassword;

    public AdminUser(
            String adminName,
            String adminNumber,
            String adminPassword,
            String adminEmail,
            String confirmAdminPassword
    ) {
        if (adminName == null || adminName.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 admin name(%s)이 들어왔습니다.", adminName));
        } else if (adminNumber == null || adminNumber.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 admin number(%s)이 들어왔습니다.", adminNumber));
        } else if (adminPassword == null || adminPassword.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 admin password(%s)이 들어왔습니다.", adminPassword));
        } else if (adminEmail == null || adminEmail.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 admin email(%s)이 들어왔습니다.", adminEmail));
        }

        this.adminName = adminName;
        this.adminNumber = adminNumber;
        this.adminPassword = adminPassword;
        this.adminEmail = adminEmail;
        this.confirmAdminPassword = confirmAdminPassword;
    }
}
