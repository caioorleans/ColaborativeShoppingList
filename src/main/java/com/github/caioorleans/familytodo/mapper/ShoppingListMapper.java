package com.github.caioorleans.familytodo.mapper;

import com.github.caioorleans.familytodo.dto.ShoppingListCreateDTO;
import com.github.caioorleans.familytodo.dto.ShoppingListDTO;
import com.github.caioorleans.familytodo.model.ShoppingList;

public interface ShoppingListMapper {
    ShoppingList toEntity(ShoppingListCreateDTO shoppingListCreateDTO);
    ShoppingListDTO toDTO(ShoppingList shoppingList);
}
