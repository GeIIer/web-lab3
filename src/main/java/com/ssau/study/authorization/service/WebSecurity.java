package com.ssau.study.authorization.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurity {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final DataSource dataSource;

    public WebSecurity(BCryptPasswordEncoder bCryptPasswordEncoder,
                       DataSource dataSource) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.dataSource = dataSource;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.jdbcAuthentication()
                .passwordEncoder(bCryptPasswordEncoder)
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT a.email, a.password, 'true' FROM accounts a " +
                        "WHERE a.email=?")
                .authoritiesByUsernameQuery(
                        "SELECT a.email, a.role FROM accounts a " +
                                "WHERE a.email=?");

        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
        http
                .csrf().disable().httpBasic().and()
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/groups/**").hasRole("ADMIN")
                        .requestMatchers("/api/services/controller/user/hello").hasRole("ADMIN")
                        .requestMatchers("/api/students/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated())
                .authenticationManager(authenticationManager)
                .formLogin();
        http.headers().frameOptions().disable();
        return http.build();
    }
}