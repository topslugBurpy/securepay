package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.model.UserCreateDto;
import com.example.demo.model.UserDto;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertToUserDto).collect(Collectors.toList());
    }

    @Override
    public String addUser(UserCreateDto userCreateDto) {
        //if email address
        try {
            String encryptedPassword = null;
            if(userCreateDto.password()!=null && userCreateDto.password()!="") {
                encryptedPassword = generatePassword(userCreateDto.password());
            }
            User newUser = new User();

            newUser.setEmail(userCreateDto.email());
            newUser.setPassword(encryptedPassword);
            newUser.setUsername(userCreateDto.username());
            newUser.setRole(userCreateDto.role());

            userRepository.save(newUser);
            return "User added successfully";
        } catch (Exception e) {
            return e.getMessage();
        }

    }


    //HELPER FUNCTIONS

    private UserDto convertToUserDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getRole(), user.getEmail(),  user.getPassword());
    }

    private String generatePassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
