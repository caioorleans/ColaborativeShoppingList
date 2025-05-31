package com.github.caioorleans.familytodo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ShoppingListDTO {

    private String id;
    private String name;
    private UserDTO owner;
    private Date creationDate;
    private List<UserDTO> members;
}
