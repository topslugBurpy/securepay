package com.example.demo.controller;


import com.example.demo.model.UserCreateDto;
import com.example.demo.model.UserDto;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/auth/api")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserCreateDto UserCreateDto) {
        var res = userService.addUser(UserCreateDto);
        return ResponseEntity.ok(res);
    }
}
