package com.project.sales.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTO {

    @Valid
    @NotNull(message = "{validation.client.notNull}")
    private EntityId client;

    @Valid
    @NotEmpty(message = "{validation.productList.empty}")
    private List<EntityId> products;

}