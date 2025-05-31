package com.github.caioorleans.familytodo.repository;

import com.github.caioorleans.familytodo.model.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, String> {
}
