package com.github.caioorleans.familytodo.repository;

import com.github.caioorleans.familytodo.model.ShoppingListItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingListItemRepository extends JpaRepository<ShoppingListItem, String> {
}
