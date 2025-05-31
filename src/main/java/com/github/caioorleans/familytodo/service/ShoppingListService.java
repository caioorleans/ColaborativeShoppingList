package com.github.caioorleans.familytodo.service;

import com.github.caioorleans.familytodo.model.ShoppingList;

import java.util.List;

public interface ShoppingListService {
    ShoppingList create(ShoppingList shoppingList);
    List<ShoppingList> findAllByLoggedUser();
}
