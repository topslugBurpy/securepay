package com.example.demo.model;

import jakarta.validation.constraints.NotNull;

public record UserDto(@NotNull Long id, @NotNull String username, @NotNull String email, @NotNull String role, @NotNull String password) {
}
