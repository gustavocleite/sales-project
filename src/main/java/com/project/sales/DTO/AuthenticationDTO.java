package com.project.sales.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDTO {

    @NotEmpty(message = "{validation.authentication.login.empty}")
    private String username;

    @NotEmpty(message = "{validation.authentication.password.empty}")
    private String password;
}
