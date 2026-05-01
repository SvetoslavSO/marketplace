package org.svetso.marketplace_monolyth.product.application.product.dto.command;

public record DeleteProductCommand(
        Long requesterId,
        Long productId
) {}
