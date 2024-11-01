package com.kobe.signUpApp.dto.user.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kobe.signUpApp.domain.user.User;
import lombok.Data;

@Data
@JsonIgnoreProperties({"password"})
public class UserResponse {
    private long id;
    private String name;
    private String email;
    private String password;
    private String confirmPassword;

    public UserResponse(
            long id,
            String name,
            String email,
            String password,
            String confirmPassword
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public UserResponse(long id, User user) {
        this.id = id;
        this.name = user.getUserName();
        this.email = user.getUserEmail();
        this.password = user.getUserPassword();
        this.confirmPassword = user.getConfirmUserPassword();
    }

    public long getId() {
        return id;
    }

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
}
