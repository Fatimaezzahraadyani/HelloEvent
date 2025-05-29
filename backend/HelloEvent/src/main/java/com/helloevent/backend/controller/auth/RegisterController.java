package com.helloevent.backend.controller.auth;


import com.helloevent.backend.dto.AuthResponse;
import com.helloevent.backend.dto.RegisterRequest;
import com.helloevent.backend.model.Role;
import com.helloevent.backend.model.User;
import com.helloevent.backend.repository.UserRepository;
import com.helloevent.backend.service.AuthService;
import com.helloevent.backend.service.JwtService;
import com.helloevent.backend.service.UserService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class RegisterController {

    private final AuthService authService;
    private final UserService userService;
    private final UserRepository userRepository;
    private JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public RegisterController(AuthService authService, UserService userService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = new JwtService();
    }

//    @PostMapping("/register")
//    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
//        AuthResponse response = authService.register(request);
//        return ResponseEntity.ok(response);
//    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        // Vérifie si l'email est déjà utilisé
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body("Erreur : cet email est déjà utilisé.");
        }

        // Crée un nouvel utilisateur
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Affecte le rôle : CLIENT par défaut
//        if (request.getRole() != null) {
//            user.setRole(request.getRole());
//        } else {
//            user.setRole(Role.Client);
//        }

        user.setRole(Role.Client);

        // save user
        userRepository.save(user);

        // Génère le token JWT avec des claims
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", user.getRole().name());

        String jwt = jwtService.generateToken(extraClaims, user);

        // Retourne le token dans la réponse
        return ResponseEntity.ok(new AuthResponse(jwt));
    }








}
