package com.github.caioorleans.familytodo.controller;

import com.github.caioorleans.familytodo.dto.ShoppingListItemCreateDTO;
import com.github.caioorleans.familytodo.dto.ShoppingListItemDTO;
import com.github.caioorleans.familytodo.mapper.ShoppingListItemMapper;
import com.github.caioorleans.familytodo.model.ShoppingListItem;
import com.github.caioorleans.familytodo.service.ShoppingListItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/shoppingListItem")
public class ShoppingListItemController {

    private final ShoppingListItemService shoppingListItemService;
    private final ShoppingListItemMapper shoppingListItemMapper;

    public ShoppingListItemController(
            ShoppingListItemService shoppingListItemService,
            ShoppingListItemMapper shoppingListItemMapper
    ) {
        this.shoppingListItemService = shoppingListItemService;
        this.shoppingListItemMapper = shoppingListItemMapper;
    }

    @PostMapping("/{listId}")
    public ResponseEntity<ShoppingListItemDTO> create(
            @PathVariable String listId,
            @RequestBody @Valid ShoppingListItemCreateDTO item
    ) {
        var createdItem = this.shoppingListItemService.create(listId, shoppingListItemMapper.toEntity(item));
        return ResponseEntity.status(HttpStatus.CREATED).body(shoppingListItemMapper.toDto(createdItem));
    }

    @PatchMapping("/{itemId}/markAsDone")
    public ResponseEntity<ShoppingListItemDTO> markAsDone(@PathVariable String itemId) {
        ShoppingListItem item = shoppingListItemService.markAsDone(itemId);
        return ResponseEntity.status(HttpStatus.OK).body(shoppingListItemMapper.toDto(item));
    }

    @PatchMapping("/{itemId}/unmarkAsDone")
    public ResponseEntity<ShoppingListItemDTO> unmarkAsDone(@PathVariable String itemId) {
        ShoppingListItem item = shoppingListItemService.unmarkAsDone(itemId);
        return ResponseEntity.status(HttpStatus.OK).body(shoppingListItemMapper.toDto(item));
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<ShoppingListItemDTO> delete(@PathVariable String itemId) {
        shoppingListItemService.delete(itemId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
