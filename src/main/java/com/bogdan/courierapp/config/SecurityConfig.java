package com.bogdan.courierapp.config;

//import com.bogdan.courierapp.repository.CourierRepository;
//import com.bogdan.courierapp.security.UserDetailsServiceImpl;
//import lombok.AllArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
public class SecurityConfig {

//    private final AuthenticationProvider authenticationProvider;

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
//                        authorizationManagerRequestMatcherRegistry
////                                .requestMatchers(
////                                        new AntPathRequestMatcher("/courier/**"),
////                                        new AntPathRequestMatcher("/courier")
////                                ).hasRole("courier")
//                                .requestMatchers("/**")
//                                .permitAll())
////                                .authenticated())
//                .formLogin(Customizer.withDefaults())
//                .logout(logoutPage -> logoutPage.logoutSuccessUrl("/"))
//                .authenticationProvider(authenticationProvider);
//
//        return http.build();
}
//}
