package com.github.caioorleans.familytodo.mapper.impl;

import com.github.caioorleans.familytodo.dto.UserCreateDTO;
import com.github.caioorleans.familytodo.mapper.UserMapper;
import com.github.caioorleans.familytodo.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperModelMapper implements UserMapper {

    private final ModelMapper modelMapper;

    public UserMapperModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public User toEntity(UserCreateDTO dto) {
        return modelMapper.map(dto, User.class);
    }
}
