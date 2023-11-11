package com.example.Lotoya_Java.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.*;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(AbstractHttpConfigurer::disable);
            http
                    .authorizeHttpRequests(
                            authorize -> authorize
                                    .requestMatchers("/static/**").permitAll()
                                    .requestMatchers("/**").permitAll()
                                    .requestMatchers("/login").permitAll()
                                    .requestMatchers("/join").permitAll()
                                    .anyRequest().authenticated()
                    );
            http
                    .formLogin(form -> form
                            .loginPage("/login")
                            .defaultSuccessUrl("/")
                            .usernameParameter("email")
                            .failureUrl("/users/login/error")
                            .permitAll());
            http
                    .logout((logout) -> logout
                            .logoutRequestMatcher(new AntPathRequestMatcher("/login"))
                            .logoutSuccessUrl("/")
                            .permitAll());

            return http.build();
        }




    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http.authorizeHttpRequests(auth -> {
//                    auth.requestMatchers("/public/**", "/login").permitAll();
//                    auth.anyRequest().authenticated();
//                })
//                .formLogin(withDefaults())
//                .csrf(AbstractHttpConfigurer::disable)
//                .formLogin(AbstractHttpConfigurer::disable)
//                .httpBasic(AbstractHttpConfigurer::disable)
//                .logout(AbstractHttpConfigurer::disable);
//
//
//        return http.build();
//    }

}