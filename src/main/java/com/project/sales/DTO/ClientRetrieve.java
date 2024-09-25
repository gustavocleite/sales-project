package com.project.sales.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.sales.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRetrieve {

    private Long id;

    private String cpf;

    private String name;

    private String email;

    private String phone;

    private AddressRetrieve address;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

    private boolean isDeleted;
}
