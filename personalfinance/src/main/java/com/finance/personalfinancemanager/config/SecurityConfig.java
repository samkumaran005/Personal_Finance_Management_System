package com.finance.personalfinancemanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
            .formLogin(login -> login.disable())
            .logout(logout -> logout
                    .logoutUrl("/spring-logout") // use a DIFFERENT path so we can keep our own /logout
                    .invalidateHttpSession(true)
                    .clearAuthentication(true));
        
        return http.build();
    }
}
