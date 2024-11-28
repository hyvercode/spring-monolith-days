package com.hyvercode.monolithdays.product.repository;

import com.hyvercode.monolithdays.product.model.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product,String> {
}
