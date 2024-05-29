package com.evalia.backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PasswordDto {

    private String oldPassword;

    private String token;

    private String newPassword;
}
