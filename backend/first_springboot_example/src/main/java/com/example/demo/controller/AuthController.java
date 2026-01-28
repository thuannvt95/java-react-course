package com.example.demo.controller;

import com.example.demo.exception.AuthException;
import com.example.demo.models.LoginRequest;
import com.example.demo.models.LoginResponse;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @RequestMapping(path = "/api/auth/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        LoginResponse response;
        try {
            response = authService.login(request.getUsername(), request.getPassword());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (AuthException e) {
            return new ResponseEntity<>(e, HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
}
