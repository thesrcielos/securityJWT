package com.example.SpringSecurityJWT.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecureConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.
        authorizeHttpRequests(request-> request.requestMatchers("/api/**").permitAll()
                .anyRequest().authenticated()).
                formLogin(form->form.permitAll()).
                sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS).
                        invalidSessionUrl("/login")).
                build();
    }
}
