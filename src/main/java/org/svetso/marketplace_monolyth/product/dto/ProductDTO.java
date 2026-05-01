package org.svetso.marketplace_monolyth.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductDTO {
    @NotNull
    private Long id;
    @NotBlank
    private String name;
    private String description;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Integer stock;
    @NotNull
    private Long categoryId;
    private LocalDateTime updatedAt;
}
