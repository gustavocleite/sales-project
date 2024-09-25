package com.project.sales.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    @NotBlank(message = "{validation.product.name.empty}")
    private String name;

    private String description;

    @PositiveOrZero(message = "{validation.price.positive}")
    private double price;

    @NotBlank(message = "{validation.product.barcode.empty}")
    @Size(min = 13, max = 13, message = "{validation.product.barcode.size}")
    @Column(length = 13, nullable = false, unique = true)
    private String barcode;

    @Past(message = "{validation.product.manufacturing.past}")
    private LocalDate manufacturing;

    private boolean isDeleted;
}
