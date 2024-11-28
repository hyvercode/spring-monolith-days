package com.hyvercode.monolithdays.product.model.entity;

import com.hyvercode.monolithdays.helpers.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
@SQLDelete(sql = "UPDATE product SET deleted_time=NOW() where product_id=?")
@Filter(name ="deletedTime",condition ="deleted_time is NULL")
public class Product extends BaseEntity {

    @Id
    @Column(name = "product_id", length = 36, nullable = false, unique = true)
    @GeneratedValue
    @UuidGenerator
    private String productId;

    @Column(name = "productCode", length = 36, nullable = false, unique = true)
    private String productCode;

    @Column(name = "barcode", length = 36, nullable = false)
    private String barcode;

    @Column(name = "sku", length = 36, nullable = false)
    private String sku;

    @Column(name = "product_name", length = 60, nullable = false)
    private String productName;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "deleted_time")
    private Timestamp deletedTime;
}