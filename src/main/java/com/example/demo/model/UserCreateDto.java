package com.example.demo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserCreateDto(@NotBlank String username, @NotBlank String email, @NotBlank String role, @NotBlank String password) {
}
