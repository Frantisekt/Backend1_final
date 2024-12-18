package com.dh.clinica.auth;

import com.dh.clinica.config.JwtService;
import com.dh.clinica.entity.Role;
import com.dh.clinica.entity.Usuario;
import com.dh.clinica.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final IUsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register (RegisterRequest request){
        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(Role.USER)
                .build();
        usuarioRepository.save(usuario);
        String token = jwtService.generateToken(usuario);
        return AuthenticationResponse.builder()
                .token(token)
                .build();

    }

    public AuthenticationResponse login(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("no existe el usuario"));

        String token = jwtService.generateToken(usuario);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
}
