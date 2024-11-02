package com.kobe.signUpApp.dto.user.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserCreateRequest {
    private String userName;
    private String userEmail;
    private String userPassword;
    private String confirmUserPassword;

    @JsonIgnore
    private Boolean isSamePassword;

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getConfirmUserPassword() {
        return confirmUserPassword;
    }

    // 패스워드 확인 로직
    public Boolean getSamePassword() {
        if (this.userPassword.equals(this.confirmUserPassword)) {
            return this.isSamePassword = true;
        } else {
            return this.isSamePassword = false;
        }
    }
}
