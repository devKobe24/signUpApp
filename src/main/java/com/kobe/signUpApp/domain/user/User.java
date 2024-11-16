package com.kobe.signUpApp.domain.user;

import com.kobe.signUpApp.domain.user.saveHistory.UserSaveHistory;
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

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private UserSaveHistory userSaveHistory;


    protected User() {}

    public User(
            String userName,
            String userEmail,
            String userPassword
    ) {
        if (userName == null || userName.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 userName(%s)이 들어왔습니다.", userName));
        } else if (userEmail == null || userEmail.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 userEmail(%s)이 들어왔습니다.", userEmail));
        } else if (userPassword == null || userPassword.isBlank()) {
            throw new IllegalArgumentException();
        }
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
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

    public UserSaveHistory getUserSaveHistory() {
        return userSaveHistory;
    }

    public void setUserSaveHistory(UserSaveHistory userSaveHistory) {
        this.userSaveHistory = userSaveHistory;
        userSaveHistory.setUser(this); // 양방향 관계 설정
    }
}
