package com.project.sales.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StatusDTO {

    @NotBlank(message = "{validation.sale.statusSale.empty}")
    private String statusSale;

}
