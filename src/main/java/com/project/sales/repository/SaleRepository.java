package com.project.sales.repository;

import com.project.sales.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Long> {

    @Query("SELECT c FROM Sale c WHERE c.client.id = :clientId")
    List<Sale> findByClientId(@Param("clientId") Long clientId);

    @Query("SELECT p FROM Sale p JOIN p.products prod WHERE prod.id = :productId")
    List<Sale> findByProductId(@Param("productId") Long productId);

}
