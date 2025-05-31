package com.github.caioorleans.familytodo.mapper;

import com.github.caioorleans.familytodo.dto.ShoppingListCompleteDTO;
import com.github.caioorleans.familytodo.dto.ShoppingListCreateDTO;
import com.github.caioorleans.familytodo.dto.ShoppingListPartialDTO;
import com.github.caioorleans.familytodo.model.ShoppingList;

public interface ShoppingListMapper {
    ShoppingList toEntity(ShoppingListCreateDTO shoppingListCreateDTO);
    ShoppingListPartialDTO toPartialDTO(ShoppingList shoppingList);
    ShoppingListCompleteDTO toCompleteDTO(ShoppingList shoppingList);
}
