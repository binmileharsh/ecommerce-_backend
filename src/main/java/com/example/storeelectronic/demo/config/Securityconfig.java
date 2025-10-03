package com.example.storeelectronic.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity(prePostEnabled = true)

public class Securityconfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.DELETE, "/user/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/user/**").hasAnyRole("ADMIN", "NORMAL")
                        .requestMatchers(HttpMethod.GET, "/product/**").permitAll()
                        .requestMatchers("/product/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/user/**").permitAll()
                        .anyRequest().authenticated() // Any other request requires authentication
                )
                ;

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
