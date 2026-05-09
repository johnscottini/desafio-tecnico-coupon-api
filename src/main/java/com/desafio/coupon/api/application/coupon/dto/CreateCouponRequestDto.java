package com.desafio.coupon.api.application.coupon.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateCouponRequestDto(
    @NotBlank(message = "code é obrigatório")
    String code,
    @NotBlank(message = "description é obrigatório")
    String description,
    @NotNull(message = "discountValue é obrigatório")
    BigDecimal discountValue,
    @NotNull(message = "expirationDate é obrigatório")
    LocalDateTime expirationDate
) {
}

