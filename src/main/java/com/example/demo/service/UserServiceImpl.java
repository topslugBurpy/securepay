package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exception.ConflictException;
import com.example.demo.model.UserCreateDto;
import com.example.demo.model.UserLoginDto;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthService authService;


    @Override
    public String addUser(UserCreateDto userCreateDto) {
        try {
            String encryptedPassword = null;

            //if encryptedPassword
            //TODO remove encryptedPassword check as the dto already has a check
            if(userCreateDto.password()!=null && !userCreateDto.password().isEmpty()) {
                encryptedPassword = authService.generatePassword(userCreateDto.password());
            }

            User newUser = new User();

            newUser.setEmail(userCreateDto.email());
            newUser.setUsername(userCreateDto.username());
            newUser.setRole(userCreateDto.role());

            //set the encrypted encryptedPassword for login
            newUser.setEncryptedPassword(encryptedPassword);

            userRepository.save(newUser);
            return "User added successfully!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public boolean verifyUser(UserLoginDto userLoginDto){
        return authService.isGenuineUser(userLoginDto);
    }

    @Override
    public boolean ifUserExists(UserCreateDto userCreateDto) {
        if(userCreateDto.username()!=null && !userCreateDto.username().isBlank() &&
                userRepository.existsByUsername(userCreateDto.username())) {
             throw new ConflictException("Username is already taken!");
        }
        if(userCreateDto.email()!=null && !userCreateDto.email().isBlank() &&
                userRepository.existsByEmail(userCreateDto.email())) {
            throw new ConflictException("Email is already taken!");
        }
        return false;
    }

}
