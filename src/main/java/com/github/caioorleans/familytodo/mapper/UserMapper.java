package com.github.caioorleans.familytodo.mapper;

import com.github.caioorleans.familytodo.dto.UserCreateDTO;
import com.github.caioorleans.familytodo.dto.UserDTO;
import com.github.caioorleans.familytodo.model.User;

public interface UserMapper {
    User toEntity(UserCreateDTO dto);
    UserDTO toDTO(User model);
}
