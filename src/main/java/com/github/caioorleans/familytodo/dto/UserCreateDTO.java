package com.github.caioorleans.familytodo.dto;

import com.github.caioorleans.familytodo.validation.decorator.PasswordMatches;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@PasswordMatches
public class UserCreateDTO {
    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String name;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "O e-mail deve ser válido")
    @Size(max = 50, message = "O e-mail deve ter no máximo 50 caracteres")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 8, max = 16, message = "A senha deve ter entre 8 e 16 caracteres")
    private String password;

    @NotBlank(message = "A confirmação de senha é obrigatória")
    private String passwordConfirmation;
}
