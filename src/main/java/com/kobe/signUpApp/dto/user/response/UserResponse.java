package com.kobe.signUpApp.dto.user.response;

import com.kobe.signUpApp.domain.user.User;

public class UserResponse {
    private long id;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String confirmUserPassword;

    public UserResponse(long id, User user) {
        this.id = id;
        this.userName = user.getUserName();
        this.userEmail = user.getUserEmail();
        this.userPassword = user.getUserPassword();
        this.confirmUserPassword = user.getConfirmUserPassword();
    }

    public long getId() {
        return id;
    }

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
