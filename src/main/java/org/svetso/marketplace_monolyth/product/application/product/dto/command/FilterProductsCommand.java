package org.svetso.marketplace_monolyth.product.application.product.dto.command;

import java.math.BigDecimal;

public record FilterProductsCommand (
        String name,
        BigDecimal price
) {}
