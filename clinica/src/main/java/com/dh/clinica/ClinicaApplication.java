package com.dh.clinica;

import com.dh.clinica.entity.Role;
import com.dh.clinica.entity.Usuario;
import com.dh.clinica.repository.IUsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
@SpringBootApplication
public class ClinicaApplication {

	public static void main(String[] args) {

		SpringApplication.run(ClinicaApplication.class, args);
	}
	@Bean
	CommandLineRunner init(IUsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder){
		return args -> {
			if(usuarioRepository.findByEmail("admin@admin.com").isEmpty()){
				Usuario admin = new Usuario();
				admin.setEmail("admin@admin.com");
				admin.setPassword(passwordEncoder.encode("admin123"));
				admin.setNombre("admin");
				admin.setApellido("admin");
				admin.setRol(Role.ADMIN);
				usuarioRepository.save(admin);
			}
		};
	}
}
