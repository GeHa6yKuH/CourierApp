package com.bogdan.courierapp.service.impl;


import com.bogdan.courierapp.config.service.JwtService;
import com.bogdan.courierapp.dto.AuthenticationRequest;
import com.bogdan.courierapp.dto.AuthenticationResponse;
import com.bogdan.courierapp.exception.CourierNotFoundException;
import com.bogdan.courierapp.exception.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class   AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getCourierPhoneNumber(),
                        request.getCourierPassword()
                )
        );
        UserDetails user = userDetailsService.loadUserByUsername(request.getCourierPhoneNumber());
        if (user == null) {
            throw new CourierNotFoundException(ErrorMessage.COURIER_NOT_FOUND);
        }
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
