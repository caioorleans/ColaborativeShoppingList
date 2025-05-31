package com.github.caioorleans.familytodo.mapper.impl;

import com.github.caioorleans.familytodo.dto.ShoppingListCreateDTO;
import com.github.caioorleans.familytodo.dto.ShoppingListDTO;
import com.github.caioorleans.familytodo.mapper.ShoppingListMapper;
import com.github.caioorleans.familytodo.model.ShoppingList;
import com.github.caioorleans.familytodo.security.AuthenticatedUserProvider;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

@Component
public class ShoppingListMapperModelMapper implements ShoppingListMapper {

    private final AuthenticatedUserProvider authenticatedUserProvider;
    private final ModelMapper modelMapper;

    public ShoppingListMapperModelMapper(AuthenticatedUserProvider authenticatedUserProvider, ModelMapper modelMapper) {
        this.authenticatedUserProvider = authenticatedUserProvider;
        this.modelMapper = modelMapper;
    }

    @Override
    public ShoppingList toEntity(ShoppingListCreateDTO shoppingListCreateDTO) {
        var shoppingList = modelMapper.map(shoppingListCreateDTO, ShoppingList.class);
        var user = authenticatedUserProvider.getUser();

        shoppingList.setOwner(user);
        shoppingList.setCreationDate(new Date());
        shoppingList.setMembers(Collections.singletonList(user));

        return shoppingList;
    }

    @Override
    public ShoppingListDTO toDTO(ShoppingList shoppingList) {
        return modelMapper.map(shoppingList, ShoppingListDTO.class);
    }
}