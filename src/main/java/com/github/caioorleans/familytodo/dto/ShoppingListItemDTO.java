package com.github.caioorleans.familytodo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class ShoppingListItemDTO extends ShoppingListItemCreateDTO{
    String id;
    UserDTO creator;
    Date creationDate;
    boolean done;
}
