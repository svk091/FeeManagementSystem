package org.example.feemanagementsystem.web;

import jakarta.validation.Valid;
import org.example.feemanagementsystem.domain.dto.auth.LoginRequest;
import org.example.feemanagementsystem.domain.entity.User;
import org.example.feemanagementsystem.exception.custom.UserAlreadyExistsException;
import org.example.feemanagementsystem.repository.UserRepository;
import org.example.feemanagementsystem.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody LoginRequest loginRequest) {
        if (userRepository.findByUsername(loginRequest.username()).isPresent()) {
            throw new UserAlreadyExistsException("Error: Username already exists");
        }
        User user = new User();
        user.setUsername(loginRequest.username());
        user.setPassword(passwordEncoder.encode(loginRequest.password()));
        userRepository.save(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("User Registered Successfull");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest request) {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.username(),
                            request.password()
                    )
            );
            String token = jwtUtil.generateToken(request.username());
            return ResponseEntity.ok( token);
        }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // remove jwt  token at client
        return ResponseEntity.ok("Logged out successfully");
    }


}
