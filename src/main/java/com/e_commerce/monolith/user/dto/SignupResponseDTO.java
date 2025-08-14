package com.e_commerce.monolith.user.dto;

import jakarta.validation.constraints.NotBlank;

public class SignupResponseDTO {
    @NotBlank
    private String email;

    @NotBlank
    private String status;
}
