package com.desafio.coupon.api.application.coupon.usecase;

import com.desafio.coupon.api.application.coupon.dto.CouponResponseDto;
import com.desafio.coupon.api.application.coupon.dto.CreateCouponRequestDto;
import com.desafio.coupon.api.builder.CreateCouponUseCaseBuilder;
import com.desafio.coupon.api.database.BaseTest;
import com.desafio.coupon.api.domain.coupon.model.CouponStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CreateCouponUseCaseScenarios extends BaseTest {

    public CreateCouponUseCaseBuilder shouldCreateCoupon() {
        UUID id = UUID.randomUUID();
        LocalDateTime expirationDate = LocalDateTime.now().plusDays(7);
        LocalDateTime creationDate = LocalDateTime.now();

        CreateCouponRequestDto input = new CreateCouponRequestDto(
                "TEST01",
                "Cupom de teste",
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

        return CreateCouponUseCaseBuilder.builder()
                .input(input)
                .output(output)
                .build();
    }
}

