package com.github.caioorleans.familytodo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TokensDTO {
    private String accessToken;
    private String refreshToken;
}
