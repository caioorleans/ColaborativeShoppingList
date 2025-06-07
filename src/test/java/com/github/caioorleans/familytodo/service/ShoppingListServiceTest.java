package com.github.caioorleans.familytodo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import com.github.caioorleans.familytodo.exception.ForbiddenException;
import com.github.caioorleans.familytodo.exception.NotFoundException;
import com.github.caioorleans.familytodo.model.ShoppingList;
import com.github.caioorleans.familytodo.model.User;
import com.github.caioorleans.familytodo.repository.ShoppingListRepository;
import com.github.caioorleans.familytodo.security.AuthenticatedUserProvider;
import com.github.caioorleans.familytodo.service.impl.ShoppingListServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ShoppingListServiceImplTest {

    @Mock
    private ShoppingListRepository shoppingListRepository;

    @Mock
    private AuthenticatedUserProvider authenticatedUserProvider;

    private ShoppingListServiceImpl shoppingListService;

    private User mockUser;
    private ShoppingList mockList;

    @BeforeEach
    void setUp() {
        shoppingListService = new ShoppingListServiceImpl(shoppingListRepository, authenticatedUserProvider);
        mockUser = new User();
        mockList = new ShoppingList();
    }

    @Test
    void shouldReturnShoppingListWhenUserIsMember() {
        mockList.setMembers(List.of(mockUser));
        when(authenticatedUserProvider.getUser()).thenReturn(mockUser);
        when(shoppingListRepository.findById("123")).thenReturn(Optional.of(mockList));

        ShoppingList result = shoppingListService.getIfAuthorizedUserIsAMember("123");

        assertEquals(mockList, result);
    }

    @Test
    void shouldThrowForbiddenExceptionWhenUserIsNotMember() {
        User anotherUser = new User();
        mockList.setMembers(List.of(anotherUser));
        when(authenticatedUserProvider.getUser()).thenReturn(mockUser);
        when(shoppingListRepository.findById("123")).thenReturn(Optional.of(mockList));

        assertThrows(ForbiddenException.class, () ->
                shoppingListService.getIfAuthorizedUserIsAMember("123")
        );
    }

    @Test
    void shouldThrowNotFoundExceptionWhenListNotFound() {
        when(shoppingListRepository.findById("123")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () ->
                shoppingListService.getIfAuthorizedUserIsAMember("123")
        );
    }
}