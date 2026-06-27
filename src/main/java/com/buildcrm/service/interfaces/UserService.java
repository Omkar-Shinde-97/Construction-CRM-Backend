package com.buildcrm.service.interfaces;

import com.buildcrm.dto.request.CreateUserRequest;
import com.buildcrm.dto.response.PagedResponse;
import com.buildcrm.dto.response.UserResponse;

import java.util.UUID;

public interface UserService {
    UserResponse createUser(CreateUserRequest request);
    UserResponse getUserById(UUID id);
    PagedResponse<UserResponse> listUsers(int page, int size, String sort);
}
