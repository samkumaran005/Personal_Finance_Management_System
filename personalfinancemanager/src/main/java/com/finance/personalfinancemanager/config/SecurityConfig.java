package com.finance.personalfinancemanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // allow static resources and api auth endpoints
                .requestMatchers("/","/index.html","/login.html","/home.html","/income.html","/expense.html","/css/**","/js/**","/api/auth/**").permitAll()
                .anyRequest().permitAll() // keep open; authentication handled by our session-based login
            );
        return http.build();
    }
}
