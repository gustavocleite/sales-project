package com.project.sales.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRetrieve {

    private Integer id;

    private String name;

    private String description;

    private double price;

    private String barcode;

    private LocalDate manufacturing;

    private boolean isDeleted;
}
