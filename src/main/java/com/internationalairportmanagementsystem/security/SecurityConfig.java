package com.internationalairportmanagementsystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private JwtAuthEntryPoint jwtAuthEntryPoint;
    private CustomUserDetailsService customUserDetailsService;
    private CustomAuthorizationManager  customAuthorizationManager;
    private JWTAuthenticationFilter  jwtAuthenticationFilter;

    @Autowired
    public SecurityConfig(
            JWTAuthenticationFilter  jwtAuthenticationFilter,
            JwtAuthEntryPoint jwtAuthEntryPoint,
            CustomUserDetailsService customUserDetailsService,
            CustomAuthorizationManager customAuthorizationManager) {
        this.jwtAuthEntryPoint = jwtAuthEntryPoint;
        this.customUserDetailsService = customUserDetailsService;
        this.customAuthorizationManager = customAuthorizationManager;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.authenticationEntryPoint(jwtAuthEntryPoint) // Set custom entry point
                )
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Stateless session
                )
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/private/**").access(customAuthorizationManager) // Custom authorization logic
                                .requestMatchers("/**").permitAll() // Permit all other requests
                                .anyRequest().permitAll() // Allow any other request
                )
                .httpBasic(httpBasic -> {}); // Enable HTTP Basic authentication

        // Add custom JWT authentication filter before UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)throws Exception{
        return configuration.getAuthenticationManager();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean JWTAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new  JWTAuthenticationFilter();
    }
}
