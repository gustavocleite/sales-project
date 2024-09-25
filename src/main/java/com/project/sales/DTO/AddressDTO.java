package com.project.sales.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    @NotEmpty(message = "{validation.address.street.empty}")
    @Size(max = 100)
    private String street;

    @Size(max = 40)
    private String number;

    @Size(max = 255)
    private String complement;

    @NotEmpty(message = "{validation.address.neighborhood.empty}")
    @Size(max = 100)
    private String neighborhood;

    @NotEmpty(message = "{validation.address.city.empty}")
    private String city;

    @NotEmpty(message = "{validation.address.state.empty}")
    private String state;

    @NotEmpty(message = "{validation.address.country.empty}")
    private String country;

    @NotEmpty(message = "{validation.address.postalCode.empty}")
    private String postalCode;

}
