/*
 * Project: coding-challenge
 *
 * Copyright © 2025 Vilua Healthcare GmbH
 */
package de.sivgin.coding_challenge.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.*;

/**
 * @author fatih
 * @since 14/09/2025
 */
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // if you don't need CSRF
                .authorizeHttpRequests(auth -> auth

                        // all unsecured stuffs here please
                        .requestMatchers("/actuator/**").permitAll()  // ✅ no authentication required
                        .requestMatchers(GET, "/api/v1/trainings").permitAll()  // ✅ no authentication required
                        .requestMatchers(POST, "/api/v1/trainings").permitAll()  // ✅TODO should be secured


                        .anyRequest().authenticated()              // everything else is secured
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        return http.build();
    }
}
