package com.misenvios.misenviosapi.service;

import com.misenvios.misenviosapi.dto.RegisterRequestDTO;
import com.misenvios.misenviosapi.dto.UserResponseDTO;

public interface IUserService {
    UserResponseDTO register(RegisterRequestDTO dto);
    UserResponseDTO getById(Long id);
    UserResponseDTO getByEmail(String email);
}
