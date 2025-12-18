package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            HandlerMappingIntrospector introspector
    ) throws Exception {

        // ✅ MVC matcher (for @RestController endpoints)
        MvcRequestMatcher.Builder mvc = new MvcRequestMatcher.Builder(introspector)
                .servletPath("/");

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                // 🔓 PUBLIC MVC ENDPOINTS
                .requestMatchers(
                        mvc.pattern("/auth/**"),
                        mvc.pattern("/v3/api-docs/**"),
                        mvc.pattern("/swagger-ui/**"),
                        mvc.pattern("/swagger-ui.html")
                ).permitAll()

                // 🔓 PUBLIC SERVLETS (NOT MVC)
                .requestMatchers(
                        new AntPathRequestMatcher("/simple-status"),
                        new AntPathRequestMatcher("/h2-console/**")
                ).permitAll()

                // 🔐 EVERYTHING ELSE
                .anyRequest().authenticated()
            )

            // Needed for H2 Console
            .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
