package com.desafio.coupon.api.application.coupon.usecase;

import com.desafio.coupon.api.application.coupon.dto.CouponResponseDto;
import com.desafio.coupon.api.builder.GetAllCouponsUseCaseBuilder;
import com.desafio.coupon.api.database.BaseTest;
import com.desafio.coupon.api.domain.coupon.model.CouponStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class GetAllCouponsUseCaseScenarios extends BaseTest {

    public GetAllCouponsUseCaseBuilder shouldGetAllCoupons() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        CouponResponseDto coupon1 = new CouponResponseDto(
                id1,
                "00AB12",
                "Cupom 1",
                BigDecimal.valueOf(10),
                now.plusDays(5),
                now.minusDays(2),
                null,
                CouponStatus.ACTIVE
        );

        CouponResponseDto coupon2 = new CouponResponseDto(
                id2,
                "00CD34",
                "Cupom 2",
                BigDecimal.valueOf(20),
                now.plusDays(10),
                now.minusDays(1),
                null,
                CouponStatus.ACTIVE
        );

        List<CouponResponseDto> output = new ArrayList<>();
        output.add(coupon1);
        output.add(coupon2);

        return GetAllCouponsUseCaseBuilder.builder()
                .output(output)
                .build();
    }

    public GetAllCouponsUseCaseBuilder shouldGetAllCouponsEmpty() {
        List<CouponResponseDto> output = new ArrayList<>();

        return GetAllCouponsUseCaseBuilder.builder()
                .output(output)
                .build();
    }
}

