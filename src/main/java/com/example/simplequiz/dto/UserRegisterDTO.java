package com.example.simplequiz.dto;

public class UserRegisterDTO {


    private String name;

    private String email;

    private String createPassword;

    private String confirmPassword;

    public UserRegisterDTO() {
    }

    public UserRegisterDTO(String name, String email, String createPassword, String confirmPassword) {
        this.name = name;
        this.email = email;
        this.createPassword = createPassword;
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreatePassword() {
        return createPassword;
    }

    public void setCreatePassword(String createPassword) {
        this.createPassword = createPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "UserRegisterDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", createPassword='" + createPassword + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
