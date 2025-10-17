package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity(name = "User")
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true, nullable = false)
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String role;
    @NotNull
    private String email;
    @NotNull
    @Column(unique = true, nullable = false)
    private String encryptedPassword;
}
