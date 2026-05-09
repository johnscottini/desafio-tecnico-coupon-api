package com.desafio.coupon.api.application.coupon.dto;

import com.desafio.coupon.api.domain.coupon.model.Coupon;
import com.desafio.coupon.api.domain.coupon.model.CouponStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CouponResponseDto(
    UUID id,
    String code,
    String description,
    BigDecimal discountValue,
    LocalDateTime expirationDate,
    LocalDateTime creationDate,
    LocalDateTime deletionDate,
    CouponStatus status
) {
    public static CouponResponseDto fromDomain(Coupon coupon) {
        return new CouponResponseDto(
            coupon.getId(),
            coupon.getCode(),
            coupon.getDescription(),
            coupon.getDiscountValue(),
            coupon.getExpirationDate(),
            coupon.getCreationDate(),
            coupon.getDeletionDate(),
            coupon.getStatus()
        );
    }
}

