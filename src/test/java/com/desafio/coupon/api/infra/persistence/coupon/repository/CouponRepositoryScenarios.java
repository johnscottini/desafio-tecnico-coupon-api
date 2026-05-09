package com.desafio.coupon.api.infra.persistence.coupon.repository;

import com.desafio.coupon.api.builder.CouponRepositoryImplBuilder;
import com.desafio.coupon.api.database.BaseTest;
import com.desafio.coupon.api.domain.coupon.model.Coupon;
import com.desafio.coupon.api.domain.coupon.model.CouponStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class CouponRepositoryScenarios  extends BaseTest {

    public CouponRepositoryImplBuilder shouldSaveCoupon() {
        Coupon couponToSave = Coupon.reconstitute(
                UUID.randomUUID(),
                "00SV01",
                "Cupom para salvar",
                BigDecimal.valueOf(10),
                LocalDateTime.now().plusDays(10),
                LocalDateTime.now().minusDays(1),
                null,
                CouponStatus.ACTIVE
        );

        return CouponRepositoryImplBuilder.builder()
                .couponToSave(couponToSave)
                .build();
    }

    public CouponRepositoryImplBuilder shouldHaveActiveAndDeletedCoupons() {
        Coupon activeCoupon = Coupon.reconstitute(
                UUID.randomUUID(),
                "00AC01",
                "Cupom ativo",
                BigDecimal.valueOf(15),
                LocalDateTime.now().plusDays(8),
                LocalDateTime.now().minusDays(1),
                null,
                CouponStatus.ACTIVE
        );

        Coupon deletedCoupon = Coupon.reconstitute(
                UUID.randomUUID(),
                "00DL01",
                "Cupom deletado",
                BigDecimal.valueOf(20),
                LocalDateTime.now().plusDays(6),
                LocalDateTime.now().minusDays(2),
                LocalDateTime.now().minusHours(3),
                CouponStatus.DELETED
        );

        return CouponRepositoryImplBuilder.builder()
                .activeCoupon(activeCoupon)
                .deletedCoupon(deletedCoupon)
                .build();
    }

    public CouponRepositoryImplBuilder shouldListOnlyActiveCoupons() {
        Coupon activeCouponOne = Coupon.reconstitute(
                UUID.randomUUID(),
                "00AC11",
                "Cupom ativo 1",
                BigDecimal.valueOf(8),
                LocalDateTime.now().plusDays(12),
                LocalDateTime.now().minusDays(4),
                null,
                CouponStatus.ACTIVE
        );

        Coupon activeCouponTwo = Coupon.reconstitute(
                UUID.randomUUID(),
                "00AC22",
                "Cupom ativo 2",
                BigDecimal.valueOf(12),
                LocalDateTime.now().plusDays(15),
                LocalDateTime.now().minusDays(5),
                null,
                CouponStatus.ACTIVE
        );

        Coupon deletedCoupon = Coupon.reconstitute(
                UUID.randomUUID(),
                "00DL22",
                "Cupom deletado",
                BigDecimal.valueOf(18),
                LocalDateTime.now().plusDays(20),
                LocalDateTime.now().minusDays(6),
                LocalDateTime.now().minusDays(1),
                CouponStatus.DELETED
        );

        return CouponRepositoryImplBuilder.builder()
                .activeCoupons(List.of(activeCouponOne, activeCouponTwo))
                .deletedCoupon(deletedCoupon)
                .build();
    }
}

