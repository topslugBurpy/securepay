package com.example.demo.model;

import jakarta.validation.constraints.NotNull;

public record UserCreateDto(@NotNull String username, @NotNull String email, @NotNull String role, @NotNull String password) {
}
