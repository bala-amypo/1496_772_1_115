@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http
        .csrf(csrf -> csrf.disable())
        .headers(headers -> headers.frameOptions(frame -> frame.disable()))

        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/h2-console/**").permitAll()
            .requestMatchers("/simple-status").permitAll()
            .requestMatchers("/api/**").permitAll()
            .anyRequest().permitAll()   // 🔥 FIX
        )

        .formLogin(form -> form.disable())
        .httpBasic(basic -> basic.disable());

    return http.build();
}
