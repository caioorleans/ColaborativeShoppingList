package com.github.caioorleans.familytodo.mapper;

import com.github.caioorleans.familytodo.dto.ShoppingListItemCreateDTO;
import com.github.caioorleans.familytodo.dto.ShoppingListItemDTO;
import com.github.caioorleans.familytodo.model.ShoppingListItem;

public interface ShoppingListItemMapper {
    ShoppingListItem toEntity(ShoppingListItemCreateDTO shoppingListItemCreateDTO);
    ShoppingListItemDTO toDto(ShoppingListItem shoppingListItem);
}
