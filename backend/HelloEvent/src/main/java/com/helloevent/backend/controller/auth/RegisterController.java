package com.helloevent.backend.controller.auth;


import com.helloevent.backend.dto.AuthResponse;
import com.helloevent.backend.dto.RegisterRequest;
import com.helloevent.backend.service.AuthService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class RegisterController {

    private final AuthService authService;

    public RegisterController(AuthService authService) {
        this.authService = authService;
    }

//    @PostMapping("/register")
//    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
//        AuthResponse response = authService.register(request);
//        return ResponseEntity.ok(response);
//    }








}
