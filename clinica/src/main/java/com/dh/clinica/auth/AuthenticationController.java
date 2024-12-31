package com.dh.clinica.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity
                .ok()
                //.header("Access-Control-Allow-Origin", "http://localhost:5173")
                //.header("Access-Control-Allow-Credentials", "true")
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

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        if (token != null && token.startsWith("Bearer ")) {
            String jwt = token.substring(7);
            authenticationService.logout(jwt);
            return ResponseEntity.ok("Sesión cerrada exitosamente");
        }
        return ResponseEntity.badRequest().body("Token no válido");
    }
}

