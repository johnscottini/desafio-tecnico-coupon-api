package com.desafio.coupon.api.domain.coupon.model;

import com.desafio.coupon.api.builder.CouponModelBuilder;
import com.desafio.coupon.api.database.BaseTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public abstract class CouponScenarios extends BaseTest {

    public CouponModelBuilder shouldCreateCouponWithNormalizedCode() {
        return CouponModelBuilder.builder()
                .rawCode("A-1B")
                .normalizedCode("000A1B")
                .description("Cupom valido")
                .discountValue(BigDecimal.valueOf(10))
                .expirationDate(LocalDateTime.now().plusDays(2))
                .build();
    }

    public CouponModelBuilder shouldNormalizeCodeWithOnlySymbols() {
        return CouponModelBuilder.builder()
                .rawCode("###")
                .normalizedCode("000000")
                .build();
    }

    public CouponModelBuilder shouldThrowInvalidCodeByBlankValue() {
        return CouponModelBuilder.builder()
                .rawCode(" ")
                .build();
    }

    public CouponModelBuilder shouldThrowInvalidCodeByLength() {
        return CouponModelBuilder.builder()
                .rawCode("ABC1234")
                .build();
    }

    public CouponModelBuilder shouldThrowInvalidDiscountByNull() {
        return CouponModelBuilder.builder()
                .rawCode("AB12")
                .description("Cupom")
                .discountValue(null)
                .expirationDate(LocalDateTime.now().plusDays(1))
                .build();
    }

    public CouponModelBuilder shouldThrowInvalidDiscountByMinimumRule() {
        return CouponModelBuilder.builder()
                .rawCode("AB12")
                .description("Cupom")
                .discountValue(BigDecimal.valueOf(0.49))
                .expirationDate(LocalDateTime.now().plusDays(1))
                .build();
    }

    public CouponModelBuilder shouldThrowInvalidExpirationByNull() {
        return CouponModelBuilder.builder()
                .rawCode("AB12")
                .description("Cupom")
                .discountValue(BigDecimal.valueOf(5))
                .expirationDate(null)
                .build();
    }

    public CouponModelBuilder shouldThrowInvalidExpirationByPastDate() {
        return CouponModelBuilder.builder()
                .rawCode("AB12")
                .description("Cupom")
                .discountValue(BigDecimal.valueOf(5))
                .expirationDate(LocalDateTime.now().minusMinutes(1))
                .build();
    }

    public CouponModelBuilder shouldDeleteActiveCoupon() {
        Coupon coupon = Coupon.reconstitute(
                UUID.randomUUID(),
                "00AB12",
                "Cupom ativo",
                BigDecimal.valueOf(10),
                LocalDateTime.now().plusDays(2),
                LocalDateTime.now().minusDays(1),
                null,
                CouponStatus.ACTIVE
        );

        return CouponModelBuilder.builder()
                .coupon(coupon)
                .build();
    }

    public CouponModelBuilder shouldThrowAlreadyDeletedCoupon() {
        Coupon coupon = Coupon.reconstitute(
                UUID.randomUUID(),
                "00AB12",
                "Cupom deletado",
                BigDecimal.valueOf(10),
                LocalDateTime.now().plusDays(2),
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now().minusHours(1),
                CouponStatus.DELETED
        );

        return CouponModelBuilder.builder()
                .coupon(coupon)
                .build();
    }

    public CouponModelBuilder shouldReconstituteCoupon() {
        return CouponModelBuilder.builder()
                .id(UUID.randomUUID())
                .normalizedCode("00ZZ99")
                .description("Cupom reconstituido")
                .discountValue(BigDecimal.valueOf(12))
                .expirationDate(LocalDateTime.now().plusDays(5))
                .creationDate(LocalDateTime.now().minusDays(2))
                .deletionDate(LocalDateTime.now().minusDays(1))
                .status(CouponStatus.DELETED)
                .build();
    }
}

