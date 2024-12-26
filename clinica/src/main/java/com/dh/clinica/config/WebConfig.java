package com.dh.clinica.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173") // Permite este origen específico
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Incluye OPTIONS para preflight
                .allowedHeaders("*") // Permite todos los encabezados
                .allowCredentials(true); // Permite credenciales
    }
}