package com.kobe.signUpApp.domain.user;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_email", nullable = false, unique = true)
    private String userEmail;

    @Column(name = "user_password", nullable = false)
    private String userPassword;
    private String confirmUserPassword;

    @Column(name = "password_match")
    private boolean isSamePassword;

    protected User() {}

    public User(
            Long id,
            String userName,
            String userEmail,
            String userPassword
            ) {
        if (userName == null || userName.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 userName(%s)이 들어왔습니다.", userName));
        } else if (userEmail == null || userEmail.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 userEmail(%s)이 들어왔습니다.", userEmail));
        } else if (userPassword == null || userPassword.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 userPassword(%s)이 들어왔습니다.", userPassword));
        }

        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public User(
            Long id,
            String userName,
            String userEmail,
            String userPassword,
            String confirmUserPassword,
            boolean isSamePassword
    ) {
        if (userName == null || userName.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 userName(%s)이 들어왔습니다.", userName));
        } else if (userEmail == null || userEmail.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 userEmail(%s)이 들어왔습니다.", userEmail));
        } else if (userPassword == null || userPassword.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 userPassword(%s)이 들어왔습니다.", userPassword));
        } else if (confirmUserPassword == null || confirmUserPassword.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 confirmUserPassword(%s)이 들어왔습니다.", confirmUserPassword));
        }

        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.confirmUserPassword = confirmUserPassword;
        this.isSamePassword = isSamePassword;
    }

    public Long getId() {
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

    public boolean isSamePassword() {
        return isSamePassword;
    }
}
