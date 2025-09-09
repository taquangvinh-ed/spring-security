package com.eazybytes.springsecuritysection2.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        //http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/myCards", "/myAccount", "myLoans", "/myBalance").authenticated()
                .requestMatchers("/notices", "/contact", "/error").permitAll());

        http.formLogin((httpSecurityFormLoginConfigurer) -> httpSecurityFormLoginConfigurer.disable());
        http.httpBasic(withDefaults());
        return http.build();
    }

}

