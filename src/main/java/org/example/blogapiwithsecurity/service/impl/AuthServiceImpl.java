package org.example.blogapiwithsecurity.service.impl;

import com.fasterxml.jackson.core.PrettyPrinter;
import org.example.blogapiwithsecurity.dto.LoginDto;
import org.example.blogapiwithsecurity.dto.RegisterDto;
import org.example.blogapiwithsecurity.entity.Role;
import org.example.blogapiwithsecurity.entity.User;
import org.example.blogapiwithsecurity.exception.BlogApiException;
import org.example.blogapiwithsecurity.repo.RoleRepo;
import org.example.blogapiwithsecurity.repo.UserRepo;
import org.example.blogapiwithsecurity.security.JwtTokenProvider;
import org.example.blogapiwithsecurity.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class AuthServiceImpl implements AuthService {


    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepo userRepo, RoleRepo roleRepo, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }
    private AuthenticationManager authenticationManager;
    private UserRepo userRepo;
    private RoleRepo roleRepo;
    private JwtTokenProvider jwtTokenProvider;
    private PasswordEncoder passwordEncoder;
    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return token;
    }
    @Override
    public String register(RegisterDto registerDto) {
        if (userRepo.existsByEmail(registerDto.getEmail())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "user is already exists");
        }
        User user = new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setUsername(registerDto.getUsername());

        Set<Role> roles = new HashSet<>();
//            Optional <Role> userRoles = roleRepo.findByName("Role_User");
//            userRoles.get();
            Role role = new Role();
            role.setName("Role_User");
            roles.add(role);
            user.setRoles(roles);
            userRepo.save(user);
            return "User is registered";
        }
        }


