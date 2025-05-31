package com.github.caioorleans.familytodo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class ShoppingListPartialDTO {

    private String id;
    private String name;
    private UserDTO owner;
    private Date creationDate;
}
