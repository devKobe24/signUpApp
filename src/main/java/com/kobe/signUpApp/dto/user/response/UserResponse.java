package com.kobe.signUpApp.dto.user.response;

import com.kobe.signUpApp.domain.user.User;
import lombok.Data;

@Data
public class UserResponse {
    private long userId;
    private String userName;
    private String userEmail;

    public UserResponse(long userId, String userName, String userEmail) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public UserResponse(long userId, User user) {
        this.userId = userId;
        this.userName = user.getUserName();
        this.userEmail = user.getUserEmail();
    }

    public long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
