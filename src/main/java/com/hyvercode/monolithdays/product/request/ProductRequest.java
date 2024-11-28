package com.hyvercode.monolithdays.product.request;

import com.hyvercode.monolithdays.helpers.base.BaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest extends BaseRequest {
    @NotNull(message = "Not null")
    @NotBlank(message = "Not blank")
    @NotEmpty(message = "Not empty")
    @Size(max = 36, message = "Max 36 characters")
    private String productCode;

    @NotNull(message = "Not null")
    @NotBlank(message = "Not blank")
    @NotEmpty(message = "Not empty")
    @Size(max = 36, message = "Max 36 characters")
    private String barcode;

    @NotNull(message = "Not null")
    @NotBlank(message = "Not blank")
    @NotEmpty(message = "Not empty")
    @Size(max = 36, message = "Max 36 characters")
    private String sku;

    @NotNull(message = "Not null")
    @NotBlank(message = "Not blank")
    @NotEmpty(message = "Not empty")
    @Size(max = 60, message = "Max 60 characters")
    private String productName;

    private BigDecimal price;

    private BigInteger stock;

    private Boolean isActive;
}