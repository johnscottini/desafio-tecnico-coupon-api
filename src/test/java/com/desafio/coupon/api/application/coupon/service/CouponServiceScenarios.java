package com.desafio.coupon.api.application.coupon.service;

import com.desafio.coupon.api.builder.CouponServiceBuilder;
import com.desafio.coupon.api.database.BaseTest;
import com.desafio.coupon.api.domain.coupon.model.Coupon;
import com.desafio.coupon.api.domain.coupon.model.CouponStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class CouponServiceScenarios extends BaseTest {

    public CouponServiceBuilder shouldCreateCoupon() {
        UUID id = UUID.randomUUID();
        String code = "TEST01";
        String description = "Cupom de teste";
        BigDecimal discountValue = BigDecimal.valueOf(15);
        LocalDateTime expirationDate = LocalDateTime.now().plusDays(7);
        LocalDateTime creationDate = LocalDateTime.now();

        Coupon output = Coupon.reconstitute(
                id,
                "TEST01",
                description,
                discountValue,
                expirationDate,
                creationDate,
                null,
                CouponStatus.ACTIVE
        );

        return CouponServiceBuilder.builder()
                .code(code)
                .description(description)
                .discountValue(discountValue)
                .expirationDate(expirationDate)
                .output(output)
                .build();
    }

    public CouponServiceBuilder shouldDeleteCoupon() {
        UUID id = UUID.randomUUID();
        String code = "AB12";
        LocalDateTime creationDate = LocalDateTime.now().minusDays(2);
        LocalDateTime expirationDate = LocalDateTime.now().plusDays(5);

        Coupon coupon = Coupon.reconstitute(
                id,
                "00AB12",
                "Cupom para deletar",
                BigDecimal.valueOf(10),
                expirationDate,
                creationDate,
                null,
                CouponStatus.ACTIVE
        );

        return CouponServiceBuilder.builder()
                .code(code)
                .output(coupon)
                .build();
    }

    public CouponServiceBuilder shouldFindAllActive() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        Coupon coupon1 = Coupon.reconstitute(
                id1,
                "00AB12",
                "Cupom 1",
                BigDecimal.valueOf(10),
                now.plusDays(5),
                now.minusDays(2),
                null,
                CouponStatus.ACTIVE
        );

        Coupon coupon2 = Coupon.reconstitute(
                id2,
                "00CD34",
                "Cupom 2",
                BigDecimal.valueOf(20),
                now.plusDays(10),
                now.minusDays(1),
                null,
                CouponStatus.ACTIVE
        );

        List<Coupon> outputList = new ArrayList<>();
        outputList.add(coupon1);
        outputList.add(coupon2);

        return CouponServiceBuilder.builder()
                .outputList(outputList)
                .build();
    }

    public CouponServiceBuilder shouldFindAllActiveEmpty() {
        List<Coupon> outputList = new ArrayList<>();

        return CouponServiceBuilder.builder()
                .outputList(outputList)
                .build();
    }
}

