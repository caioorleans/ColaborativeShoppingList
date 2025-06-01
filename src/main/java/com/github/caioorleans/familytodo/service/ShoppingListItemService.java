package com.github.caioorleans.familytodo.service;

import com.github.caioorleans.familytodo.model.ShoppingListItem;

public interface ShoppingListItemService {
    ShoppingListItem create(String listId, ShoppingListItem item);
}
