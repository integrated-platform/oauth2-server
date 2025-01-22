package com.example.oauthserver.config;

import com.example.oauthserver.service.UserVerificationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@Configuration
public class CustomAuthenticationManager implements AuthenticationManager {

    private final UserService userService;

    public CustomAuthenticationManager(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (userVerificationService.verifyUser(username, password)) {
            return new UsernamePasswordAuthenticationToken(username, password, authentication.getAuthorities());
        } else {
            throw new RuntimeException("Invalid username or password");
        }
    }
}
