package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // Disable CSRF for H2 + APIs
            .csrf(csrf -> csrf.disable())

            // Allow frames for H2 console
            .headers(headers -> headers.frameOptions(frame -> frame.disable()))

            .authorizeHttpRequests(auth -> auth
                // H2 console
                .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()

                // Custom servlet
                .requestMatchers(new AntPathRequestMatcher("/simple-status")).permitAll()

                // Your APIs
                .requestMatchers(new AntPathRequestMatcher("/api/**")).permitAll()

                // Everything else secured
                .anyRequest().authenticated()
            )

            // Disable login page
            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable());

        return http.build();
    }
}
