package com.github.caioorleans.familytodo.controller;

import com.github.caioorleans.familytodo.dto.ShoppingListCompleteDTO;
import com.github.caioorleans.familytodo.dto.ShoppingListCreateDTO;
import com.github.caioorleans.familytodo.dto.ShoppingListPartialDTO;
import com.github.caioorleans.familytodo.mapper.ShoppingListMapper;
import com.github.caioorleans.familytodo.service.ShoppingListService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    ShoppingListPartialDTO createShoppingList(@RequestBody @Valid ShoppingListCreateDTO shoppingListCreateDTO) {
        var shoppingList = shoppingListService.create(shoppingListMapper.toEntity(shoppingListCreateDTO));
        return shoppingListMapper.toPartialDTO(shoppingList);
    }

    @GetMapping("/{id}")
    ShoppingListCompleteDTO findShoppingListById(@PathVariable String id) {
        var shoppingList = shoppingListService.getIfAuthorizedUserIsAMember(id);
        return shoppingListMapper.toCompleteDTO(shoppingList);
    }

    @GetMapping("/findAllByLoggedUser")
    List<ShoppingListPartialDTO> findAllByLoggedUser() {
        return shoppingListService.findAllByLoggedUser().stream().map(shoppingListMapper::toPartialDTO).toList();
    }

    @PatchMapping("/{id}")
    ShoppingListCompleteDTO updateName(
            @PathVariable String id,
            @RequestBody @Valid ShoppingListCreateDTO shoppingListCreateDTO
    ) {
        var updatedList = shoppingListService.updateShoppingListName(id, shoppingListCreateDTO);
        return shoppingListMapper.toCompleteDTO(updatedList);
    }

    @DeleteMapping("/{id}")
    void deleteShoppingList(@PathVariable String id) {
        shoppingListService.deleteById(id);
    }
}
