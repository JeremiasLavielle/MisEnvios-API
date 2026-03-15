package com.misenvios.misenviosapi.controller;

import com.misenvios.misenviosapi.dto.LoginRequestDTO;
import com.misenvios.misenviosapi.security.JwtService;
import com.misenvios.misenviosapi.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO dto) {
        System.out.println(">>> Intentando login con email: " + dto.getEmail());
        System.out.println(">>> Password recibido: " + dto.getPassword());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getEmail());
        String token = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(token);
    }
}