package com.desafio.coupon.api.infra.controller.coupon;

import com.desafio.coupon.api.application.coupon.dto.CouponResponseDto;
import com.desafio.coupon.api.application.coupon.dto.CreateCouponRequestDto;
import com.desafio.coupon.api.builder.CouponControllerBuilder;
import com.desafio.coupon.api.database.BaseTest;
import com.desafio.coupon.api.domain.coupon.model.CouponStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class CouponControllerScenarios extends BaseTest {

    public CouponControllerBuilder shouldGetAllCoupons() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        CouponResponseDto coupon1 = new CouponResponseDto(
                id1,
                "00AB12",
                random.nextObject(String.class),
                BigDecimal.valueOf(10),
                now.plusDays(5),
                now.minusDays(2),
                null,
                CouponStatus.ACTIVE
        );

        CouponResponseDto coupon2 = new CouponResponseDto(
                id2,
                "00CD34",
                random.nextObject(String.class),
                BigDecimal.valueOf(20),
                now.plusDays(10),
                now.minusDays(1),
                null,
                CouponStatus.ACTIVE
        );

        List<CouponResponseDto> outputList = new ArrayList<>();
        outputList.add(coupon1);
        outputList.add(coupon2);

        return CouponControllerBuilder.builder()
                .outputList(outputList)
                .build();
    }

    public CouponControllerBuilder shouldGetAllCouponsEmpty() {
        List<CouponResponseDto> outputList = new ArrayList<>();

        return CouponControllerBuilder.builder()
                .outputList(outputList)
                .build();
    }

    public CouponControllerBuilder shouldCreateCoupon() {
        UUID id = UUID.randomUUID();
        LocalDateTime expirationDate = LocalDateTime.now().plusDays(7);
        LocalDateTime creationDate = LocalDateTime.now();

        CreateCouponRequestDto input = new CreateCouponRequestDto(
                "TEST01",
                random.nextObject(String.class),
                BigDecimal.valueOf(15),
                expirationDate
        );

        CouponResponseDto output = new CouponResponseDto(
                id,
                "00TEST01",
                input.description(),
                input.discountValue(),
                input.expirationDate(),
                creationDate,
                null,
                CouponStatus.ACTIVE
        );

        return CouponControllerBuilder.builder()
                .input(input)
                .output(output)
                .build();
    }

    public CouponControllerBuilder shouldDeleteCoupon() {
        String codeToDelete = "00AB12";

        return CouponControllerBuilder.builder()
                .codeToDelete(codeToDelete)
                .build();
    }
}



