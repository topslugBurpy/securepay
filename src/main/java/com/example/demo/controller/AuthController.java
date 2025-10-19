package com.example.demo.controller;

import com.example.demo.SpringSecurity.JwtService;
import com.example.demo.model.UserCreateDto;
import com.example.demo.model.UserLoginDto;

import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@AllArgsConstructor
@RequestMapping("/auth/api")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserCreateDto userCreateDto) {
        userService.ifUserExists(userCreateDto);
        var res = userService.addUser(userCreateDto);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto) {
        boolean isUserVerified = userService.verifyUser(userLoginDto);
        if (!isUserVerified) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = jwtService.generateToken(userLoginDto.username());
        return ResponseEntity.accepted().body(Map.of("token", token));
    }
}
