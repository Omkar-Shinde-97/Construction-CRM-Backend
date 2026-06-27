package com.buildcrm.service.interfaces;

import com.buildcrm.dto.request.LoginRequest;
import com.buildcrm.dto.request.RefreshTokenRequest;
import com.buildcrm.dto.response.AuthResponse;
import com.buildcrm.dto.response.UserResponse;

public interface AuthService {
    AuthResponse authenticate(LoginRequest request);
    AuthResponse refreshToken(RefreshTokenRequest request);
    void logout(String refreshToken);
    UserResponse getCurrentUser();
}
