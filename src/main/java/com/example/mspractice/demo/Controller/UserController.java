package com.example.mspractice.demo.Controller;

import com.example.mspractice.demo.Service.WebClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final WebClientService webClientService;

    public UserController(WebClientService webClientService) {
        this.webClientService = webClientService;
    }

    @GetMapping("/all")
    public String getAllUsers(){
        String token = webClientService.getToken();
        return webClientService.callExternalApi1(token);
    }



    @GetMapping("/test-api")
    public String testApi(){
        return "test success";
    }
}
