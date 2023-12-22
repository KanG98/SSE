package com.kang98.service.serviceauth.controller;

import com.kang98.service.serviceauth.dto.AuthRequest;
import com.kang98.service.serviceauth.dto.AuthResponse;
import com.kang98.service.serviceauth.service.helpers.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetAuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtHelper jwtHelper;

    @Autowired
    public GetAuthController(AuthenticationManager authenticationManager, JwtHelper jwtHelper) {
        this.authenticationManager = authenticationManager;
        this.jwtHelper = jwtHelper;
    }

    @PostMapping("/authenticate")
    public AuthResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        if(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())).isAuthenticated()) {
//        if (authentication.isAuthenticated()) {
            String token = jwtHelper.generateToken(authRequest.getUsername());
            return AuthResponse.builder().jwt_token(token).build();
        }
        else {
            throw new UsernameNotFoundException("invalid user request");
        }
    }
}
