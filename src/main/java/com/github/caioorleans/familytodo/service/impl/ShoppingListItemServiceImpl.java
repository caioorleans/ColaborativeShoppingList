package com.github.caioorleans.familytodo.service.impl;

import com.github.caioorleans.familytodo.exception.ForbiddenException;
import com.github.caioorleans.familytodo.exception.NotFoundException;
import com.github.caioorleans.familytodo.model.ShoppingListItem;
import com.github.caioorleans.familytodo.model.User;
import com.github.caioorleans.familytodo.repository.ShoppingListItemRepository;
import com.github.caioorleans.familytodo.security.AuthenticatedUserProvider;
import com.github.caioorleans.familytodo.service.ShoppingListItemService;
import com.github.caioorleans.familytodo.service.ShoppingListService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ShoppingListItemServiceImpl implements ShoppingListItemService {

    private final AuthenticatedUserProvider authenticatedUserProvider;
    private final ShoppingListItemRepository shoppingListItemRepository;
    private final ShoppingListService shoppingListService;

    public ShoppingListItemServiceImpl(
            AuthenticatedUserProvider authenticatedUserProvider,
            ShoppingListItemRepository shoppingListItemRepository,
            ShoppingListService shoppingListService
    ) {
        this.authenticatedUserProvider = authenticatedUserProvider;
        this.shoppingListItemRepository = shoppingListItemRepository;
        this.shoppingListService = shoppingListService;
    }

    @Override
    public ShoppingListItem create(String listId, ShoppingListItem item) {
        var shoppingList = shoppingListService.getIfAuthorizedUserIsAMember(listId);
        var membersMap = shoppingList.getMembers().stream()
                .collect(Collectors.toMap(User::getEmail, Function.identity()));
        var user = membersMap.get(authenticatedUserProvider.getEmail());

        item.setCreator(user);
        item.setCreationDate(new Date());
        item.setShoppingList(shoppingList);

        return shoppingListItemRepository.save(item);
    }

    @Override
    public ShoppingListItem markAsDone(String itemId) {
        return updateIsDoneIfAuthenticatedUserIsAMember(itemId, true);
    }

    @Override
    public ShoppingListItem unmarkAsDone(String itemId) {
        return updateIsDoneIfAuthenticatedUserIsAMember(itemId, false);
    }

    private ShoppingListItem updateIsDoneIfAuthenticatedUserIsAMember(String itemId, boolean done) {
        var item = shoppingListItemRepository.findById(itemId).orElseThrow(() -> new NotFoundException("Item not found"));
        if (item.getShoppingList().getMembers().stream()
                .noneMatch(u -> u.getEmail().equals(authenticatedUserProvider.getEmail()))) {
            throw new ForbiddenException("You have to be a member of the list");
        }
        item.setDone(done);
        return shoppingListItemRepository.save(item);
    }

    @Override
    public void delete(String itemId) {
        shoppingListItemRepository.deleteById(itemId);
    }
}
