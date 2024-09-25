package com.project.sales.services;

import com.project.sales.DTO.ProductDTO;
import com.project.sales.DTO.ProductRetrieve;

import java.util.List;

public interface ProductService {

    ProductRetrieve create(ProductDTO productDTO);

    ProductRetrieve findById(Long id);

    List<ProductRetrieve> findAll();

    ProductRetrieve update(Long id, ProductDTO productDTO);

    void delete(Long id);
}
