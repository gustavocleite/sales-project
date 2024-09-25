package com.project.sales.services;

import com.project.sales.DTO.ProductDTO;
import com.project.sales.DTO.ProductRetrieve;
import com.project.sales.mapper.ProductMapper;
import com.project.sales.model.Product;
import com.project.sales.model.Sale;
import com.project.sales.model.StatusSale;
import com.project.sales.repository.ProductRepository;
import com.project.sales.repository.SaleRepository;
import com.project.sales.utilitis.exception.BadRequestException;
import com.project.sales.utilitis.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final MessageSource messageSource;

    private final SaleRepository saleRepository;

    @Override
    @Transactional(rollbackFor = Error.class)
    public ProductRetrieve create(ProductDTO productDTO) {
        Product product = productMapper.fromDTO(productDTO);

        product = productRepository.save(product);

        return productMapper.fromEntity(product);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductRetrieve findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageSource.getMessage("service.product.not.found.id", null, Locale.getDefault())));

        return productMapper.fromEntity(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductRetrieve> findAll() {
        List<Product> productList = productRepository.findAll();

        if(productList.isEmpty()) {
            throw new BadRequestException(messageSource.getMessage("service.product.list.empty", null, Locale.getDefault()));
        }

        return productList.stream().map(productMapper::fromEntity).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Error.class)
    public ProductRetrieve update(Long id, ProductDTO productDTO) {
        Product persistedProduct = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageSource.getMessage("service.product.not.found.id", null, Locale.getDefault())));

        Product updatedProduct = productMapper.fromDTO(productDTO);

        updateProduct(updatedProduct,persistedProduct);

        persistedProduct = productRepository.save(persistedProduct);

        return productMapper.fromEntity(persistedProduct);
    }

    private void updateProduct(Product updatedProduct, Product persistedProduct) {
        persistedProduct.setName(updatedProduct.getName());

        persistedProduct.setDescription(updatedProduct.getDescription());

        persistedProduct.setPrice(updatedProduct.getPrice());

        persistedProduct.setDeleted(updatedProduct.isDeleted());
    }

    @Override
    @Transactional(rollbackFor = Error.class)
    public void delete(Long id) {
        Product persistedProduct = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageSource.getMessage("service.product.not.found.id", null, Locale.getDefault())));

        if(persistedProduct.isDeleted()) throw new BadRequestException(messageSource.getMessage("service.product.is.deleted", null, Locale.getDefault()));

        if(searchProductInSale(persistedProduct.getId())) {
            persistedProduct.setDeleted(true);
            productRepository.save(persistedProduct);
            log.info("Produto desativado com sucesso!");
        } else {
            throw new BadRequestException(messageSource.getMessage("service.product.in.sale.not.delete", null, Locale.getDefault()));
        }
    }

    private boolean searchProductInSale(Long id) {
        List<Sale> listSale = saleRepository.findByProductId(id);
        if(listSale.isEmpty()) return true;

        for (Sale sale : listSale) {
            if (sale.getStatus().equals(StatusSale.PENDENTE)) return false;
        }

        return true;
    }
}
