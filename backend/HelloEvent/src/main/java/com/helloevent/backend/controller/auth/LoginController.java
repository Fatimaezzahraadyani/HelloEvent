package com.helloevent.backend.controller.auth;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/auth")

public class LoginController {
        @PostMapping("/authenticate")
        public ResponseEntity<AuthenticationResponse> register(
                @RequestBody AuthenticationRequest request
        ){
            //
            return null;
        }

}


