package com.github.caioorleans.familytodo.service.impl;

import com.github.caioorleans.familytodo.model.ShoppingList;
import com.github.caioorleans.familytodo.repository.ShoppingListRepository;
import com.github.caioorleans.familytodo.security.AuthenticatedUserProvider;
import com.github.caioorleans.familytodo.service.ShoppingListService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingListServiceImpl implements ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    public ShoppingListServiceImpl(
            ShoppingListRepository shoppingListRepository,
            AuthenticatedUserProvider authenticatedUserProvider
    ) {
        this.shoppingListRepository = shoppingListRepository;
        this.authenticatedUserProvider = authenticatedUserProvider;
    }

    @Override
    public ShoppingList create(ShoppingList shoppingList) {
        try {
            return shoppingListRepository.save(shoppingList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ShoppingList> findAllByLoggedUser() {
        var user = authenticatedUserProvider.getUser();
        try {
            return shoppingListRepository.getShoppingListByMembersContains(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
