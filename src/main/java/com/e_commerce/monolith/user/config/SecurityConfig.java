package com.e_commerce.monolith.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity in development, consider enabling for production APIs
            .authorizeHttpRequests(authorize -> authorize
                    // Allow unauthenticated access to Swagger UI and API docs
                    .requestMatchers(
                            "/api/users/v1/**",
                            "/swagger-ui/**",          // Swagger UI static resources (HTML, CSS, JS)
                            "/v3/api-docs/**",         // OpenAPI 3 documentation endpoint
                            "/swagger-ui.html"         // Main Swagger UI entry point (older versions might use this directly)
                    ).permitAll()
                    // Any other request requires authentication
                    .anyRequest().authenticated()
            );
    // .httpBasic(); // If you use basic authentication
    // .formLogin(); // If you use form login

    return http.build();
}

}
