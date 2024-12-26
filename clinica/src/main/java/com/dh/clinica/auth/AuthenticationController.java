package com.dh.clinica.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", "http://localhost:5173")
                .header("Access-Control-Allow-Credentials", "true")
                .body(authenticationService.register(request));
    }

    @PostMapping("/login")
    ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @GetMapping("/buscarusuario")
    ResponseEntity<AuthenticationResponse> buscarusuario(@PathVariable AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.buscarusuario(request));
    }
}
