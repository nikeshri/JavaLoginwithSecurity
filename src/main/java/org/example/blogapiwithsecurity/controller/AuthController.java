package org.example.blogapiwithsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.example.blogapiwithsecurity.dto.JWTauthResponse;
import org.example.blogapiwithsecurity.dto.LoginDto;
import org.example.blogapiwithsecurity.dto.RegisterDto;
import org.example.blogapiwithsecurity.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

@RequestMapping("/api/auth")
public class AuthController {
@Autowired
 private AuthService authService;


    @PostMapping(value = "/login")
    public ResponseEntity<JWTauthResponse> login(@RequestBody LoginDto loginDto)
    {
        String token = authService.login(loginDto);
        JWTauthResponse jwTauthResponse = new JWTauthResponse();
        jwTauthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwTauthResponse);
    }

    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
