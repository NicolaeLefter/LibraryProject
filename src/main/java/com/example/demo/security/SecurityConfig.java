package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

       // http.csrf(t -> t.disable());
       // http.authorizeHttpRequests(a -> a.anyRequest().authenticated());
       // http.oauth2ResourceServer(t -> t.jwt(Customizer.withDefaults()));
       // http.sessionManagement(t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
       // http.authorizeRequests().requestMatchers("/api/*").hasRole("ADMIN");
       // http.authorizeRequests().anyRequest().authenticated();

        http.authorizeRequests()
                //.requestMatchers("/save", "/get").permitAll()
                .requestMatchers("/api/*").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer(t -> t.jwt(a ->a.jwtAuthenticationConverter(new KeycloakJwtAuthenticationConverter(Arrays.asList("account")))))
                .cors().disable().csrf().disable();



        return http.build();
    }
}




   /* @Bean
    @Order(1)
    public SecurityFilterChain security(HttpSecurity httpSecurity) throws Exception {


        httpSecurity.authorizeRequests()
                .requestMatchers("/save", "/get").permitAll()
                .requestMatchers("/api/smart/update/*").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .cors().disable().csrf().disable();

        httpSecurity.oauth2Login();

        return httpSecurity.build();

     //   httpSecurity.formLogin(Customizer.withDefaults());  //toate requesturile sa fie autentificate(autorizate)    // sa fie autentificarea prin form

    }

    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.oauth2ResourceServer((a)-> a.jwt());
        return httpSecurity.build();
    }
    @Bean
    public AuthenticationManager  authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class).build();
    }
    /*@Bean
    public BCryptPasswordEncoder cryptPasswordEncoder(){

        return new BCryptPasswordEncoder();

    } */






