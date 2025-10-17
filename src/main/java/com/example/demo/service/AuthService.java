package com.example.demo.service;

import com.example.demo.model.UserLoginDto;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;



@Component
@AllArgsConstructor
public class AuthService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public boolean isGenuineUser(UserLoginDto userLoginDto){
        //check for username
        var user = userRepository.findByUsername(userLoginDto.username());
        String encodedPassword = user.getEncryptedPassword();
        CharSequence rawPassword = userLoginDto.password();
        if(user != null){
            //match with DB
            return passwordEncoder.matches(rawPassword, encodedPassword);
        }
        return false;
    }

    public String generatePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
