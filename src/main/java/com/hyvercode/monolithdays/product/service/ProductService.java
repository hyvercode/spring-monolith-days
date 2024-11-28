package com.hyvercode.monolithdays.product.service;

import com.hyvercode.monolithdays.constants.ErrorConstant;
import com.hyvercode.monolithdays.exception.BusinessException;
import com.hyvercode.monolithdays.helpers.base.EmptyResponse;
import com.hyvercode.monolithdays.product.model.entity.Product;
import com.hyvercode.monolithdays.product.repository.ProductRepository;
import com.hyvercode.monolithdays.product.request.ProductRequest;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> all() {
        Iterable<Product> products = productRepository.findAll();
        List<Product> productResponses = new ArrayList<>();
        products.forEach(product -> productResponses.add(Product.builder()
                .productId(product.getProductId())
                .productCode(product.getProductCode())
                .barcode(product.getBarcode())
                .sku(product.getSku())
                .productName(product.getProductName())
                .price(product.getPrice())
                .isActive(product.getIsActive())
                .build())
        );
        return productResponses;
    }

    /**
     * Create
     *
     * @param request
     * @return
     */
    @Transactional
    public EmptyResponse create(ProductRequest request) {
        Product product = Product.builder()
                .productCode(request.getProductCode())
                .barcode(request.getBarcode())
                .sku(request.getSku())
                .productName(request.getProductName())
                .price(request.getPrice())
                .isActive(request.getIsActive())
                .build();
        product.setCreatedBy("System");
        product.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        productRepository.save(product);
        return new EmptyResponse();
    }

    /**
     * Find By ID
     *
     * @param id
     * @return
     */
    public Product findById(String id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            log.info(ErrorConstant.ERROR_MESSAGE_01 + "{}", id);
            throw new BusinessException(HttpStatus.CONFLICT, ErrorConstant.ERROR_CODE_01, ErrorConstant.ERROR_MESSAGE_01);
        }
        Product product = optionalProduct.get();

        return Product.builder()
                .productId(product.getProductId())
                .sku(product.getSku())
                .productName(product.getProductName())
                .price(product.getPrice())
                .isActive(product.getIsActive())
                .build();
    }

    @Transactional
    public EmptyResponse update(String id, ProductRequest request) {

        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            log.info(ErrorConstant.ERROR_MESSAGE_01 + "{}", id);
            throw new BusinessException(HttpStatus.CONFLICT, ErrorConstant.ERROR_CODE_01, ErrorConstant.ERROR_MESSAGE_01);
        }

        Product product = optionalProduct.get();
        product.setProductName(request.getProductName());
        product.setPrice(request.getPrice());
        product.setIsActive(request.getIsActive());
        product.setUpdatedBy("System");
        product.setUpdatedTime(new Timestamp(System.currentTimeMillis()));
        productRepository.save(product);

        return new EmptyResponse();
    }

    /**
     * Delete
     *
     * @param id
     * @return
     */
    public EmptyResponse delete(String id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            log.info(ErrorConstant.ERROR_MESSAGE_01 + "{}", id);
            throw new BusinessException(HttpStatus.CONFLICT, ErrorConstant.ERROR_CODE_01, ErrorConstant.ERROR_MESSAGE_01);
        }
        productRepository.delete(optionalProduct.get());
        return new EmptyResponse();
    }
}
