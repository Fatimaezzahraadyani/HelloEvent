package com.helloevent.backend.controller.auth;


import com.helloevent.backend.dto.AuthRequest;
import com.helloevent.backend.dto.AuthResponse;
import com.helloevent.backend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/auth")



public class LoginController {

    public final AuthService authService;
    public LoginController(final AuthService authService) {
        this.authService = authService;
    }
        @PostMapping("/authenticate")
        public ResponseEntity<AuthResponse> register(
                @RequestBody AuthRequest request
        ){
            String jwt = authService.login(request);
            return ResponseEntity.ok(new AuthResponse(jwt));
        }

}


