package com.example.oauthserver.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UserService {

    private final WebClient webClient;

    public UserService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://api-server-url.com").build();
    }

    public boolean verifyUser(String username, String password) {
        // API 서버에 사용자 검증 요청
        Boolean isValid = this.webClient.post()
                .uri("/api/users/verify")
                .bodyValue(new LoginRequest(username, password))
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        return Boolean.TRUE.equals(isValid);
    }

    private static class LoginRequest {
        private String username;
        private String password;

        // Constructor, getters, setters
    }
}
