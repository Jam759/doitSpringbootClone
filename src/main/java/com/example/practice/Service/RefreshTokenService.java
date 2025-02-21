package com.example.practice.Service;

import com.example.practice.Repository.RefreshTokenRepository;
import com.example.practice.domain.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken){
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow( () -> new IllegalArgumentException("Unexpected token"));
    }

}
