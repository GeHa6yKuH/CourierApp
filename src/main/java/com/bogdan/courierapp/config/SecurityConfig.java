package com.bogdan.courierapp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    private static final String[] PERMIT_ALL_LIST = {
            "/order/**",
            "/statistics/**",
    };

    private static final String[] SWAGGER_LIST = {
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/",
            "/swagger-resources",
            "/swagger-resources/",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/",
            "/webjars/",
            "/swagger-ui.html",
            "/swagger-ui/",
            "/CourierApp/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(SWAGGER_LIST).permitAll()
                                .requestMatchers(HttpMethod.GET, PERMIT_ALL_LIST).permitAll()
                                .requestMatchers("/statistics**").hasRole("courier")
                                .requestMatchers("/api/authenticate").permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider);
        return httpSecurity.build();
    }
}
