package com.kobe.signUpApp.domain.user;

public class User {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;

    public User(
            String username,
            String email,
            String password,
            String confirmPassword
    ) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 username(%s)이 들어왔습니다.", username));
        } else if (email == null || email.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 email(%s)이 들어왔습니다.", email));
        } else if (password == null || password.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 password(%s)이 들어왔습니다.", password));
        } else if (confirmPassword == null || confirmPassword.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 confirmPassword(%s)이 들어왔습니다.", confirmPassword));
        }


        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
