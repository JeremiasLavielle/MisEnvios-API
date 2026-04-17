package com.misenvios.misenviosapi.controller;

import com.misenvios.misenviosapi.dto.RegisterRequestDTO;
import com.misenvios.misenviosapi.dto.UserResponseDTO;
import com.misenvios.misenviosapi.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/users")

public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody RegisterRequestDTO dto) {
        UserResponseDTO response = userService.register(dto);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> me(Principal principal) {
        String email = principal.getName();
        UserResponseDTO user = userService.getByEmail(email);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> user(@PathVariable Long id){
        UserResponseDTO response = userService.getById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-email")
    public ResponseEntity<UserResponseDTO> getByEmail(@RequestParam String email) {
        UserResponseDTO user = userService.getByEmail(email);
        return ResponseEntity.ok(user);
    }
}
