package com.github.caioorleans.familytodo.controller;

import com.github.caioorleans.familytodo.dto.ShoppingListCreateDTO;
import com.github.caioorleans.familytodo.dto.ShoppingListDTO;
import com.github.caioorleans.familytodo.mapper.ShoppingListMapper;
import com.github.caioorleans.familytodo.service.ShoppingListService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/shoppingLists")
public class ShoppingListController {

    private final ShoppingListService shoppingListService;
    private final ShoppingListMapper shoppingListMapper;

    public ShoppingListController(ShoppingListService shoppingListService, ShoppingListMapper shoppingListMapper) {
        this.shoppingListService = shoppingListService;
        this.shoppingListMapper = shoppingListMapper;
    }

    @PostMapping
    ShoppingListDTO createShoppingList(ShoppingListCreateDTO shoppingListCreateDTO) {
        var shoppingList = shoppingListService.create(shoppingListMapper.toEntity(shoppingListCreateDTO));
        return shoppingListMapper.toDTO(shoppingList);
    }
}
