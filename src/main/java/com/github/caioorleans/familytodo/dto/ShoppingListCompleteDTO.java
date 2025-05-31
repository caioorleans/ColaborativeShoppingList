package com.github.caioorleans.familytodo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ShoppingListCompleteDTO extends ShoppingListPartialDTO{

    private List<UserDTO> members;
}
