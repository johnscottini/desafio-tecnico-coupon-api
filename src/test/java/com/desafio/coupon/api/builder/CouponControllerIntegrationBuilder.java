package com.desafio.coupon.api.builder;

import com.desafio.coupon.api.application.coupon.dto.CreateCouponRequestDto;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class CouponControllerIntegrationBuilder {

    private String validCode;
    private String validDescription;
    private BigDecimal validDiscountValue;
    private LocalDateTime validExpirationDate;
    private String invalidCode;
    private BigDecimal invalidDiscountValue;
    private LocalDateTime invalidExpirationDate;
    private CreateCouponRequestDto validRequest;
    private CreateCouponRequestDto invalidRequest;
    private String expectedErrorMessage;
}

