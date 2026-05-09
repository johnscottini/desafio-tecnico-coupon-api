package com.desafio.coupon.api.builder;

import com.desafio.coupon.api.application.coupon.dto.CouponResponseDto;
import com.desafio.coupon.api.application.coupon.dto.CreateCouponRequestDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CouponControllerBuilder {

    private CreateCouponRequestDto input;
    private CouponResponseDto output;
    private String codeToDelete;
    private List<CouponResponseDto> outputList;
}

