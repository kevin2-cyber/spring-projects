package com.kimikevin.springsecex.service;

import com.kimikevin.springsecex.model.Users;
import com.kimikevin.springsecex.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public Users register(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repo.save(user);
        return user;
    }

    public String verify(Users user) {
        Authentication authentication =
                authManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                user.getUsername(),
                                user.getPassword()
                        )
                );
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        }
            return "Failed";
    }
}
