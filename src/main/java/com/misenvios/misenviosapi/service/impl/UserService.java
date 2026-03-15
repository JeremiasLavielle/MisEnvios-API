package com.misenvios.misenviosapi.service.impl;

import com.misenvios.misenviosapi.dto.RegisterRequestDTO;
import com.misenvios.misenviosapi.dto.UserResponseDTO;
import com.misenvios.misenviosapi.mapper.UserMapper;
import com.misenvios.misenviosapi.model.User;
import com.misenvios.misenviosapi.repository.UserRepository;
import com.misenvios.misenviosapi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO register(RegisterRequestDTO dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        User user = UserMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        User savedUser = userRepository.save(user);
        return UserMapper.toDto(savedUser);
    }

    @Override
    public UserResponseDTO getById(Long id) {
        User userById = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.toDto(userById);
    }

    @Override
    public UserResponseDTO getByEmail(String email) {
        User userByEmail = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email not found"));
        return UserMapper.toDto(userByEmail);
    }
}
