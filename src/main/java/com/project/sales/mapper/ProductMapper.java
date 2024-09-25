package com.project.sales.mapper;

import com.project.sales.DTO.ProductDTO;
import com.project.sales.DTO.ProductRetrieve;
import com.project.sales.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductRetrieve fromEntity(Product product);

    Product fromDTO(ProductDTO productDTO);
}
