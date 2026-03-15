package com.misenvios.misenviosapi.mapper;

import com.misenvios.misenviosapi.dto.RegisterRequestDTO;
import com.misenvios.misenviosapi.dto.UserResponseDTO;
import com.misenvios.misenviosapi.model.User;

public class UserMapper {

    public static User toEntity(RegisterRequestDTO u) {
        if (u == null) return null;
        return User.builder()
                .firstName(u.getFirstName())
                .lastName(u.getLastName())
                .email(u.getEmail())
                .password(u.getPassword())
                .address(u.getAddress())
                .build();
    }

    public static UserResponseDTO toDto(User u) {
        if (u == null) return null;
        return UserResponseDTO.builder()
                .id(u.getId())
                .firstName(u.getFirstName())
                .lastName(u.getLastName())
                .email(u.getEmail())
                .address(u.getAddress())
                .build();
    }

}

