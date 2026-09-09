package com.example.simplequiz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;

    @NotBlank
    @NotNull
    private String username;

    private LocalDateTime createdAt;


    @NotBlank(message="email can not blank ")
    @NotNull(message = "email can not be null ")
    @Email
    private String email;

    @NotBlank(message = "role can not be blank ")
    @NotNull
    private String Role;

    @NotBlank(message = "password can not be blank ")
    @NotNull
    private String password;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<QUIZ> quizzes;

    public User(String userId, String username, String email, String role, String password) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        Role = role;
        this.password = password;
    }

    public User(String username, String email, String role, String password) {
        this.username = username;
        this.email = email;
        Role = role;
        this.password = password;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<QUIZ> getQuizzes() {
        return quizzes;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", Role='" + Role + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public boolean isEnabled() {
    return true;
    }

    public void setCreatedAt(LocalDateTime now) {
    }
}
