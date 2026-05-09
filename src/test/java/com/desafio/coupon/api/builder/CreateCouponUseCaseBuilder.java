package com.desafio.coupon.api.builder;

import com.desafio.coupon.api.application.coupon.dto.CouponResponseDto;
import com.desafio.coupon.api.application.coupon.dto.CreateCouponRequestDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCouponUseCaseBuilder {

    private CreateCouponRequestDto input;
    private CouponResponseDto output;
}

