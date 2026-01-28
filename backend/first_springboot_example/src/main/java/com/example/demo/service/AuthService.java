package com.example.demo.service;

import com.example.demo.exception.AuthException;
import com.example.demo.models.LoginResponse;

import javax.naming.AuthenticationException;

public interface AuthService {
    public LoginResponse login(String username, String password) throws AuthException;
}
