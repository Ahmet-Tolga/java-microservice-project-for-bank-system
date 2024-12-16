package com.bank_account.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.bank_account.app.filter.JwtAuthFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;


    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.csrf().disable()
            .authorizeExchange()
               .pathMatchers("/api/v1/auth/**","/swagger-ui/**").permitAll()
               .anyExchange().authenticated()
            .and().httpBasic().and()
            .addFilterAt(jwtAuthFilter, SecurityWebFiltersOrder.FIRST);
        return http.build();
    }
}
