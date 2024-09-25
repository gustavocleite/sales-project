package com.project.sales.services;

import com.project.sales.DTO.SaleDTO;
import com.project.sales.DTO.SaleRetrieve;
import com.project.sales.DTO.StatusDTO;

import java.util.List;

public interface SaleService {

    SaleRetrieve create(SaleDTO entity);

    SaleRetrieve findById(Long id);

    List<SaleRetrieve> findAll();

    SaleRetrieve update(Long id, StatusDTO statusDTO);

    void delete(Long id);
}
