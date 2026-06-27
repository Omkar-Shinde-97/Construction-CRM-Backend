package com.buildcrm.service.impl;

import com.buildcrm.dto.request.CreateUserRequest;
import com.buildcrm.dto.response.PagedResponse;
import com.buildcrm.dto.response.UserResponse;
import com.buildcrm.entity.UserEntity;
import com.buildcrm.exception.ResourceNotFoundException;
import com.buildcrm.mapper.UserMapper;
import com.buildcrm.repository.UserRepository;
import com.buildcrm.service.interfaces.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    @SuppressWarnings("null")
    public UserResponse createUser(CreateUserRequest request) {
        UserEntity user = UserEntity.builder()
            .id(UUID.randomUUID())
            .username(request.getUsername())
            .email(request.getEmail())
            .fullName(request.getFullName())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(request.getRole())
            .phone(request.getPhone())
            .active(true)
            .build();
        UserEntity savedUser = Objects.requireNonNull(userRepository.save(user));
        return userMapper.toResponse(savedUser);
    }

    @Override
    public UserResponse getUserById(UUID id) {
        return userRepository.findByIdAndDeletedFalse(id)
            .map(userMapper::toResponse)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Override
    public PagedResponse<UserResponse> listUsers(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size);
        var result = userRepository.findAll(pageable);
        return new PagedResponse<>(
            result.getContent().stream().map(userMapper::toResponse).collect(Collectors.toList()),
            result.getNumber(),
            result.getSize(),
            result.getTotalElements(),
            result.getTotalPages(),
            result.isFirst(),
            result.isLast()
        );
    }
}
