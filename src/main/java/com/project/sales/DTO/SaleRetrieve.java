package com.project.sales.DTO;

import com.project.sales.model.StatusSale;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleRetrieve {

    private Long id;

    private ClientRetrieve client;

    private List<ProductRetrieve> products;

    private LocalDateTime date;

    private BigDecimal amount;

    private StatusSale status;

    private boolean isDeleted = false;
}
