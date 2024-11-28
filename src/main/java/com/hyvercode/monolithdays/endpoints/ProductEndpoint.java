package com.hyvercode.monolithdays.endpoints;

import com.hyvercode.monolithdays.helpers.base.EmptyResponse;
import com.hyvercode.monolithdays.product.model.entity.Product;
import com.hyvercode.monolithdays.product.request.ProductRequest;
import com.hyvercode.monolithdays.product.service.ProductService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

import java.util.List;

@BrowserCallable
@AnonymousAllowed
public class ProductEndpoint {
    private final ProductService productService;

    public ProductEndpoint(ProductService productService) {
        this.productService = productService;
    }

    public List<Product> findAll() {
        return productService.all();
    }

    public Product find(String id) {
        return productService.findById(id);
    }

    public EmptyResponse store(ProductRequest request) {
        return productService.create(request);
    }

    public EmptyResponse edit(String id, ProductRequest request) {
        return productService.update(id, request);
    }

    public EmptyResponse destroy(String id) {
        return productService.delete(id);
    }
}
