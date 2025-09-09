package com.eazybytes.springsecuritysection2.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        //http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());
        http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/myCards", "/myAccount", "myLoans", "/myBalance").authenticated()
                .requestMatchers("/notices", "/contact", "/error", "/register").permitAll());

        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {

    /// /        UserDetails user = User.withUsername("user").password("{noop}12345").authorities("read").build();
    /// /        UserDetails admin = User.withUsername("admin").password("{bcrypt}$2a$12$kihGxlx32BMSR.yIuqmh3O01JwO5UjAdn6926dm6/nhr/09sftUba").authorities("admin").build();
    /// /        return new InMemoryUserDetailsManager(user, admin);
//        return new JdbcUserDetailsManager(dataSource);
//    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}

