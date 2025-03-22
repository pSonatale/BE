package com.codeagain.sonatale.tts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/tts/**").permitAll()  // ✅ 이 경로는 인증 없이 허용
                .anyRequest().authenticated(); // 나머지는 인증 필요

        return http.build();
    }
}
