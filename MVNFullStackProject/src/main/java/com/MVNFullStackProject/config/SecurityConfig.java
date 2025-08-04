
package com.MVNFullStackProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.Customizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // Disable CSRF for REST APIs
            .cors(Customizer.withDefaults()) // Enable CORS
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**","/fuel/**","/mechanic/**").permitAll()
                .anyRequest().authenticated() // Other requests need authentication
            )
            .httpBasic(Customizer.withDefaults()); // Enable basic auth

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

