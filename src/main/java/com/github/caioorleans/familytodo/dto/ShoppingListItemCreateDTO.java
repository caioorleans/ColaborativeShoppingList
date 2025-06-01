package com.github.caioorleans.familytodo.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ShoppingListItemCreateDTO {

    @NotBlank(message = "O nome do item é obrigatório.")
    @Size(max = 100, message = "O nome do item deve ter no máximo 100 caracteres.")
    private String name;

    @NotNull(message = "A quantidade é obrigatória.")
    @Min(value = 1, message = "A quantidade deve ser no mínimo 1.")
    private Integer quantity;
}

