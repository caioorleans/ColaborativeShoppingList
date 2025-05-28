package com.github.caioorleans.familytodo.mapper;

import com.github.caioorleans.familytodo.dto.UserCreateDTO;
import com.github.caioorleans.familytodo.model.User;

public interface UserMapper {
    public User toEntity(UserCreateDTO dto);
}
