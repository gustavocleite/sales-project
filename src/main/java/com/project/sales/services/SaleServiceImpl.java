package com.project.sales.services;

import com.project.sales.DTO.SaleDTO;
import com.project.sales.DTO.SaleRetrieve;
import com.project.sales.DTO.StatusDTO;
import com.project.sales.mapper.SaleMapper;
import com.project.sales.model.Client;
import com.project.sales.model.Product;
import com.project.sales.model.Sale;
import com.project.sales.model.StatusSale;
import com.project.sales.repository.ClientRepository;
import com.project.sales.repository.ProductRepository;
import com.project.sales.repository.SaleRepository;
import com.project.sales.utilitis.exception.BadRequestException;
import com.project.sales.utilitis.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;

    private final ClientRepository clientRepository;

    private final ProductRepository productRepository;

    private final SaleMapper saleMapper;

    private final MessageSource messageSource;

    @Override
    @Transactional
    public SaleRetrieve create(SaleDTO saleDTO) {
        Client client = clientRepository.findById(saleDTO.getClient().getId())
                .orElseThrow(() -> new NotFoundException(messageSource.getMessage("service.sale.client.not.found.by.id", null, Locale.getDefault())));

        List<Product> updatedProductList = getUpdatedProductList(saleDTO);
        Sale sale = new Sale(client, updatedProductList);

        updateDate(sale);
        updateAmount(sale);
        sale.setStatus(StatusSale.PENDENTE);

        sale = saleRepository.save(sale);

        return saleMapper.fromEntity(sale);
    }

    private void updateDate(Sale sale) {
        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        LocalDateTime formattedDateTimeObj = LocalDateTime.parse(formattedDateTime, formatter);

        sale.setDate(formattedDateTimeObj);
    }

    private void updateAmount(Sale sale) {
        BigDecimal amount = BigDecimal.ZERO;

        for (Product product : sale.getProducts()) {
            amount = amount.add(product.getPrice());
        }

        sale.setAmount(amount);
    }

    private List<Product> getUpdatedProductList(SaleDTO saleDTO) {
        List<Product> productList = new ArrayList<>();

        saleDTO.getProducts().forEach(productDTO -> {
            Product product = productRepository.findById(productDTO.getId())
                    .orElseThrow(() -> new NotFoundException(messageSource.getMessage("service.sale.product.not.found.by.id", null, Locale.getDefault())));
            productList.add(product);
        });
        return productList;
    }

    @Override
    @Transactional(readOnly = true)
    public SaleRetrieve findById(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageSource.getMessage("service.sale.sale.not.found.by.id", null, Locale.getDefault())));

        return saleMapper.fromEntity(sale);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SaleRetrieve> findAll() {
        List<Sale> saleList = saleRepository.findAll();

        if(saleList.isEmpty()) {
            throw new NotFoundException(messageSource.getMessage("service.sale.list.empty", null, Locale.getDefault()));
        }

        return saleList.stream().map(saleMapper::fromEntity).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SaleRetrieve update(Long id, StatusDTO saleDTO) {
        Sale persistedSale = saleRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(messageSource.getMessage("service.sale.sale.not.found.by.id", null, Locale.getDefault())));

        StatusSale(persistedSale, saleDTO);

        persistedSale = saleRepository.save(persistedSale);

        return saleMapper.fromEntity(persistedSale);
    }

    private void StatusSale(Sale sale , StatusDTO statusDTO) {
        sale.setStatus(StatusSale.valueOf(statusDTO.getStatusSale()));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageSource.getMessage("service.sale.sale.not.found.by.id", null, Locale.getDefault())));

        sale.setDeleted(true);

        saleRepository.save(sale);
    }
}
