package com.example.demo.service;

import com.example.demo.model.UserCreateDto;
import com.example.demo.model.UserDto;
import com.example.demo.model.UserLoginDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> getAllUsers();
    String addUser(UserCreateDto userCreateDto);
    boolean verifyUser(UserLoginDto userLoginDto);
}
