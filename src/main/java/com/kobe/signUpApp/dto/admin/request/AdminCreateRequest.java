package com.kobe.signUpApp.dto.admin.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AdminCreateRequest {
    private String adminName;
    private String adminNumber;
    private String adminPassword;
    private String adminEmail;
    private String confirmAdminPassword;

    @JsonIgnore
    private Boolean isSamePassword;

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

    // 패스워드 확인 로직
    public Boolean getIsSamePassword() {
        if (this.adminPassword.equals(this.confirmAdminPassword)) {
            return this.isSamePassword = true;
        } else {
            return this.isSamePassword = false;
        }
    }
}
