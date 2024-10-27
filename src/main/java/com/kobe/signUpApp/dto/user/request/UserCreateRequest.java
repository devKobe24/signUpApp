package com.kobe.signUpApp.dto.user.request;

public class UserCreateRequest {
    private String userName;
    private String userEmail;
    private String userPassword;
    private String confirmUserPassword;

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
}
