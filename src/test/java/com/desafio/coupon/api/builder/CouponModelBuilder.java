package com.desafio.coupon.api.builder;

import com.desafio.coupon.api.domain.coupon.model.Coupon;
import com.desafio.coupon.api.domain.coupon.model.CouponStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class CouponModelBuilder {

    private UUID id;
    private String rawCode;
    private String normalizedCode;
    private String description;
    private BigDecimal discountValue;
    private LocalDateTime expirationDate;
    private LocalDateTime creationDate;
    private LocalDateTime deletionDate;
    private CouponStatus status;
    private Coupon coupon;
}

