package com.bank.app.models;

import lombok.Data;

import java.util.List;

@Data
public class LoginResponse {
    private String token;
    private String username;
    private List<String> roles;
}
