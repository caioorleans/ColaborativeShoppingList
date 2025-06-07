package com.github.caioorleans.familytodo.controller;

import com.github.caioorleans.familytodo.dto.ShoppingListCompleteDTO;
import com.github.caioorleans.familytodo.dto.ShoppingListCreateDTO;
import com.github.caioorleans.familytodo.dto.ShoppingListPartialDTO;
import com.github.caioorleans.familytodo.mapper.ShoppingListMapper;
import com.github.caioorleans.familytodo.service.ShoppingListService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<ShoppingListPartialDTO> createShoppingList(@RequestBody @Valid ShoppingListCreateDTO shoppingListCreateDTO) {
        var shoppingList = shoppingListService.create(shoppingListMapper.toEntity(shoppingListCreateDTO));
        var dto =  shoppingListMapper.toPartialDTO(shoppingList);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/{id}")
    ResponseEntity<ShoppingListCompleteDTO> findShoppingListById(@PathVariable String id) {
        var shoppingList = shoppingListService.getIfAuthorizedUserIsAMember(id);
        return ResponseEntity.ok(shoppingListMapper.toCompleteDTO(shoppingList));
    }

    @GetMapping("/findAllByLoggedUser")
    ResponseEntity<List<ShoppingListPartialDTO>> findAllByLoggedUser() {
        var dto = shoppingListService.findAllByLoggedUser().stream().map(shoppingListMapper::toPartialDTO).toList();
        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/{id}")
    ResponseEntity<ShoppingListCompleteDTO> updateName(
            @PathVariable String id,
            @RequestBody @Valid ShoppingListCreateDTO shoppingListCreateDTO
    ) {
        var updatedList = shoppingListService.updateShoppingListName(id, shoppingListCreateDTO);
        return ResponseEntity.ok(shoppingListMapper.toCompleteDTO(updatedList));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteShoppingList(@PathVariable String id) {
        shoppingListService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
