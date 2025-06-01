package com.github.caioorleans.familytodo.mapper.impl;

import com.github.caioorleans.familytodo.dto.ShoppingListItemCreateDTO;
import com.github.caioorleans.familytodo.dto.ShoppingListItemDTO;
import com.github.caioorleans.familytodo.mapper.ShoppingListItemMapper;
import com.github.caioorleans.familytodo.model.ShoppingListItem;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ShoppingItemMapperModelMapper implements ShoppingListItemMapper {

    private final ModelMapper modelMapper;

    public ShoppingItemMapperModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ShoppingListItem toEntity(ShoppingListItemCreateDTO shoppingListItemCreateDTO) {
        return modelMapper.map(shoppingListItemCreateDTO, ShoppingListItem.class);
    }

    @Override
    public ShoppingListItemDTO toDto(ShoppingListItem shoppingListItem) {
        return modelMapper.map(shoppingListItem, ShoppingListItemDTO.class);
    }
}
