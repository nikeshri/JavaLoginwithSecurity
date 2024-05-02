package org.example.blogapiwithsecurity.service;

import org.example.blogapiwithsecurity.dto.LoginDto;
import org.example.blogapiwithsecurity.dto.RegisterDto;



public interface AuthService{

    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
