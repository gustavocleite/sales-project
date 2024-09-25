package com.project.sales.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.sales.utilitis.validation.Name;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    @CPF
    @NotEmpty(message = "{validation.cpf.empty}")
    private String cpf;

    @Name
    @NotEmpty(message = "{validation.name.empty}")
    private String name;

    @Email
    @NotEmpty(message = "{validation.email.empty}")
    private String email;

    private String phone;

    @Valid
    @NotNull
    private AddressDTO address;

    @NotNull(message = "{validation.client.dateOfBirth}")
    @Past(message = "{validation.client.dateOfBirth.past}")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

    private boolean isDeleted = false;

}