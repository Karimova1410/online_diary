package com.example.demo.service;

import com.example.demo.dto.AuthLoginRequest;
import com.example.demo.dto.AuthLoginResponse;
import com.example.demo.dto.UserRegisterRequest;
import com.example.demo.entities.User;

public interface AuthService {
    void register(UserRegisterRequest userRegisterRequest);

    AuthLoginResponse login(AuthLoginRequest authLoginRequest);
    User getUsernameFromToken(String token);
}
