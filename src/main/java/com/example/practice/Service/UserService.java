package com.example.practice.Service;


import com.example.practice.Controller.DTO.AddUserRequest;
import com.example.practice.Repository.UserRepository;
import com.example.practice.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long Save(AddUserRequest dto){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(encoder.encode(dto.getPassword()))
                .build()).getId();

    }

    public User findById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow( () -> new IllegalArgumentException("Unexpected User"));
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow( () -> new IllegalArgumentException("Unexpected User"));
    }

}
