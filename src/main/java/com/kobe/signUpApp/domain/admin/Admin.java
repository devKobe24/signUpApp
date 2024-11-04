package com.kobe.signUpApp.domain.admin;

import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "admin_name", nullable = false)
    private String adminName;

    @Column(name = "admin_number", nullable = false)
    private String adminNumber;

    @Column(name = "admin_email", nullable = false, unique = true)
    private String adminEmail;

    @Column(name = "admin_password", nullable = false)
    private String adminPassword;
    private String confirmAdminPassword;

    @Column(name = "password_match", nullable = false)
    private boolean isSamePassword;

    protected Admin() {}

    public Admin(
            Long id,
            String adminName,
            String adminNumber,
            String adminEmail,
            String adminPassword
    ) {
        if (adminName == null || adminName.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 admin name(%s)이 들어왔습니다.", adminName));
        } else if (adminNumber == null || adminNumber.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 admin number(%s)이 들어왔습니다.", adminNumber));
        } else if (adminEmail == null || adminEmail.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 admin email(%s)이 들어왔습니다.", adminEmail));
        } else if (adminPassword == null || adminPassword.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 admin password(%s)이 들어왔습니다.", adminPassword));
        }

        this.id = id;
        this.adminName = adminName;
        this.adminNumber = adminNumber;
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
    }

    public Admin(
            String adminName,
            String adminNumber,
            String adminEmail,
            String adminPassword,
            String confirmAdminPassword
    ) {
        if (adminName == null || adminName.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 admin name(%s)이 들어왔습니다.", adminName));
        } else if (adminNumber == null || adminNumber.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 admin number(%s)이 들어왔습니다.", adminNumber));
        } else if (adminEmail == null || adminEmail.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 admin email(%s)이 들어왔습니다.", adminEmail));
        } else if (adminPassword == null || adminPassword.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 admin password(%s)이 들어왔습니다.", adminPassword));
        }

        this.adminName = adminName;
        this.adminNumber = adminNumber;
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
        this.confirmAdminPassword = confirmAdminPassword;
    }

    public Long getId() {
        return id;
    }

    public String getAdminName() {
        return adminName;
    }

    public String getAdminNumber() {
        return adminNumber;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public String getConfirmAdminPassword() {
        return confirmAdminPassword;
    }

    public boolean isSamePassword() {
        return isSamePassword;
    }
}
