package com.example.mspractice.demo.Service;

import com.example.mspractice.demo.Model.TokenRequest;
import com.example.mspractice.demo.Model.TokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class WebClientService {

    @Value("${token.api.email}")
    private String email;

    @Value("${token.api.password}")
    private String password;

    private final WebClient webClient;

    public WebClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    public String getToken() {
        TokenRequest tokenRequest = new TokenRequest();
        tokenRequest.setEmail(email);
        tokenRequest.setPassword(password);

        System.out.println("Email: " + email);
        System.out.println("Password: " + password);

        Map<String, String> loginBody = Map.of(
                "email", email,
                "password", password
        );

        TokenResponse tokenResponse = webClient.post()
                .uri("/api/login")
                .bodyValue(loginBody)
                .retrieve()
                .bodyToMono(TokenResponse.class)
                .block();

        return tokenResponse.getToken();
    }

    public String callExternalApi1(String token){
        return webClient.get()
                .uri("/api/users?page=2")
                .headers(headers ->
                        headers.setBearerAuth(token))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String callExternalApi() {
        return webClient.get()
                .uri("/basic-auth/myuser/mypass")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
