package org.svetso.marketplace_monolyth.product.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateProductDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private Long categoryId;
}
