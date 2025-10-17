package com.example.demo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserLoginDto(@NotNull @NotBlank String username, @NotNull @NotBlank String password) {
}
