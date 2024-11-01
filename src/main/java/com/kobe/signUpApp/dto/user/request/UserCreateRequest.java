package com.kobe.signUpApp.dto.user.request;

public class UserCreateRequest {
    private String name;
    private String email;
    private String password;
    private String confirmPassword;
    private Boolean isSamePassword;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    // 패스워드 확인 로직
    public Boolean getSamePassword() {
        if (this.password.equals(this.confirmPassword)) {
            return this.isSamePassword = true;
        } else {
            return this.isSamePassword = false;
        }
    }
}
