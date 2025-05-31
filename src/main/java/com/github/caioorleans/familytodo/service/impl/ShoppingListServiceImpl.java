package com.github.caioorleans.familytodo.service.impl;

import com.github.caioorleans.familytodo.model.ShoppingList;
import com.github.caioorleans.familytodo.repository.ShoppingListRepository;
import com.github.caioorleans.familytodo.service.ShoppingListService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingListServiceImpl implements ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;

    public ShoppingListServiceImpl(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    @Override
    public ShoppingList create(ShoppingList shoppingList) {
        try {
            return shoppingListRepository.save(shoppingList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
