package com.buildcrm.service.impl;

import com.buildcrm.dto.request.LoginRequest;
import com.buildcrm.dto.request.RefreshTokenRequest;
import com.buildcrm.dto.response.AuthResponse;
import com.buildcrm.dto.response.UserResponse;
import com.buildcrm.entity.UserEntity;
import com.buildcrm.exception.ResourceNotFoundException;
import com.buildcrm.mapper.UserMapper;
import com.buildcrm.repository.UserRepository;
import com.buildcrm.security.jwt.JwtTokenProvider;
import com.buildcrm.service.interfaces.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public AuthResponse authenticate(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserEntity user = userRepository.findByUsernameAndDeletedFalse(request.getUsername())
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        String accessToken = jwtTokenProvider.createAccessToken(user.getUsername(), user.getRole().name());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getUsername());
        user.setRefreshToken(refreshToken);
        userRepository.save(user);
        return new AuthResponse(accessToken, refreshToken);
    }

    @Override
    @Transactional
    public AuthResponse refreshToken(RefreshTokenRequest request) {
        UserEntity user = userRepository.findByRefreshTokenAndDeletedFalse(request.getRefreshToken())
            .orElseThrow(() -> new ResourceNotFoundException("Refresh token not found"));
        String accessToken = jwtTokenProvider.createAccessToken(user.getUsername(), user.getRole().name());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getUsername());
        user.setRefreshToken(refreshToken);
        userRepository.save(user);
        return new AuthResponse(accessToken, refreshToken);
    }

    @Override
    @Transactional
    public void logout(String refreshToken) {
        userRepository.findByRefreshTokenAndDeletedFalse(refreshToken).ifPresent(user -> {
            user.setRefreshToken(null);
            userRepository.save(user);
        });
    }

    @Override
    public UserResponse getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResourceNotFoundException("Authenticated user not found");
        }
        return userRepository.findByUsernameAndDeletedFalse(authentication.getName())
            .map(userMapper::toResponse)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
