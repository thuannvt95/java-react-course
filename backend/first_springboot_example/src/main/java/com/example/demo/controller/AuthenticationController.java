package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequestMapping("/api/auth")
@PreAuthorize("hasRole('ADMIN')")
public class AuthenticationController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/encrypt")
    public ResponseEntity<?> encryptPassword(@RequestParam("password") String password) {
        return new ResponseEntity<>(passwordEncoder.encode(password), HttpStatus.OK);
    }
}
