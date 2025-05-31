package com.github.caioorleans.familytodo.repository;

import com.github.caioorleans.familytodo.model.ShoppingList;
import com.github.caioorleans.familytodo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, String> {
    List<ShoppingList> getShoppingListByMembersContains(User user);
}
