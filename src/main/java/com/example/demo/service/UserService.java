package com.example.demo.service;

import com.example.demo.model.UserCreateDto;
import com.example.demo.model.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    String addUser(UserCreateDto userCreateDto);
}
