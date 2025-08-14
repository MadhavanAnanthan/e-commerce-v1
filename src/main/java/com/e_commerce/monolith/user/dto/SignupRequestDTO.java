package com.e_commerce.monolith.user.dto;

import jakarta.validation.constraints.NotBlank;

public class SignupRequestDTO {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    public @NotBlank String getEmail() {
        return email;
    }

    public @NotBlank String getPassword() {
        return password;
    }

    public @NotBlank String getConfirmPassword() {
        return confirmPassword;
    }

    public @NotBlank String getFirstName() {
        return firstName;
    }

    public @NotBlank String getLastName() {
        return lastName;
    }

    public void setPassword(String password){
        this.password=password;
    }
}
