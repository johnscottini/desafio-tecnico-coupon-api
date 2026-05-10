package com.desafio.coupon.api.infra.controller.coupon;

import com.desafio.coupon.api.builder.CouponControllerIntegrationBuilder;
import com.desafio.coupon.api.application.coupon.dto.CreateCouponRequestDto;
import com.desafio.coupon.api.database.BaseTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class CouponControllerIntegrationScenarios extends BaseTest {

    protected static final String API_URL = "/api/coupons";

    public CouponControllerIntegrationBuilder shouldCreateValidCoupon() {
        LocalDateTime validExpirationDate = LocalDateTime.now().plusDays(3);
        CreateCouponRequestDto validRequest = new CreateCouponRequestDto(
                "AB12",
                "Cupom de teste integracao",
                BigDecimal.valueOf(10),
                validExpirationDate
        );

        return CouponControllerIntegrationBuilder.builder()
                .validCode("AB12")
                .validDescription("Cupom de teste integracao")
                .validDiscountValue(BigDecimal.valueOf(10))
                .validExpirationDate(validExpirationDate)
                .validRequest(validRequest)
                .build();
    }

    public CouponControllerIntegrationBuilder shouldFailCreateByInvalidCode() {
        LocalDateTime validExpirationDate = LocalDateTime.now().plusDays(3);
        CreateCouponRequestDto invalidRequest = new CreateCouponRequestDto(
                "CODEINVALIDODEMAIS",
                "Cupom",
                BigDecimal.valueOf(10),
                validExpirationDate
        );

        return CouponControllerIntegrationBuilder.builder()
                .invalidRequest(invalidRequest)
                .expectedErrorMessage("O código do cupom não pode ter mais de 6 caracteres.")
                .build();
    }

    public CouponControllerIntegrationBuilder shouldFailCreateByInvalidDiscount() {
        LocalDateTime validExpirationDate = LocalDateTime.now().plusDays(3);
        CreateCouponRequestDto invalidRequest = new CreateCouponRequestDto(
                "AB123",
                "Cupom",
                BigDecimal.valueOf(0.25),
                validExpirationDate
        );

        return CouponControllerIntegrationBuilder.builder()
                .invalidRequest(invalidRequest)
                .expectedErrorMessage("O valor de desconto precisa ser no mínimo R$0,5.")
                .build();
    }

    public CouponControllerIntegrationBuilder shouldFailCreateByInvalidExpirationDate() {
        LocalDateTime invalidExpirationDate = LocalDateTime.now().minusMinutes(5);
        CreateCouponRequestDto invalidRequest = new CreateCouponRequestDto(
                "AB12",
                "Cupom",
                BigDecimal.valueOf(10),
                invalidExpirationDate
        );

        return CouponControllerIntegrationBuilder.builder()
                .invalidRequest(invalidRequest)
                .expectedErrorMessage("O cupom não pode ser criado com data de expiração no passado.")
                .build();
    }
}

