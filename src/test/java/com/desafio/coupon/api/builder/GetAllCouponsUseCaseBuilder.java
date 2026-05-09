package com.desafio.coupon.api.builder;

import com.desafio.coupon.api.application.coupon.dto.CouponResponseDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetAllCouponsUseCaseBuilder {

    private List<CouponResponseDto> output;
}

