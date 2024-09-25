package com.project.sales.mapper;

import com.project.sales.DTO.SaleDTO;
import com.project.sales.DTO.SaleRetrieve;
import com.project.sales.model.Sale;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SaleMapper {

    SaleRetrieve fromEntity(Sale sale);

    Sale fromDTO(SaleDTO saleDTO);
}
