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
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long Save(AddUserRequest dto){

        Optional<User> temp = userRepository.findByEmail(dto.getEmail());

        if(temp.isPresent()){
            return 0L;
        }else{
            return userRepository.save(User.builder()
                    .email(dto.getEmail())
                    .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                    .build()).getId();
        }

    }

    public User findById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow( () -> new IllegalArgumentException("Unexpected User"));
    }

}
