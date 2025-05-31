package com.github.caioorleans.familytodo.service.impl;

import com.github.caioorleans.familytodo.dto.ShoppingListCreateDTO;
import com.github.caioorleans.familytodo.exception.ForbiddenException;
import com.github.caioorleans.familytodo.exception.NotFoundException;
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

    @Override
    public ShoppingList getIfAuthorizedUserIsAMember(String id) {
        var user = authenticatedUserProvider.getUser();
        var shoppingList = findByIdOrThrowNotFound(id);
        if(!shoppingList.getMembers().contains(user)) {
            throw new ForbiddenException("User is not a member of this list");
        }
        return shoppingList;
    }

    @Override
    public ShoppingList updateShoppingListName(String id, ShoppingListCreateDTO shoppingListCreateDTO) {
        var user = authenticatedUserProvider.getUser();
        var shoppingList = findByIdOrThrowNotFound(id);
        if (!shoppingList.getOwner().equals(user)) {
            throw new ForbiddenException("Shopping list name can only be updated by its owner");
        }
        shoppingList.setName(shoppingListCreateDTO.getName());
        return shoppingListRepository.save(shoppingList);

    }

    @Override
    public void deleteById(String id) {
        var user = authenticatedUserProvider.getUser();
        var shoppingList = findByIdOrThrowNotFound(id);
        if (!shoppingList.getOwner().equals(user)) {
            throw new ForbiddenException("Shopping list name can only be deleted by its owner");
        }
        shoppingListRepository.delete(shoppingList);
    }

    private ShoppingList findByIdOrThrowNotFound(String id) {
        return shoppingListRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Shopping list not found")
        );
    }
}
