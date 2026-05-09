package com.desafio.coupon.api.application.coupon.usecase;

import com.desafio.coupon.api.builder.DeleteCouponUseCaseBuilder;
import com.desafio.coupon.api.commons.test.TestTags;
import com.desafio.coupon.api.application.coupon.service.CouponService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import static org.mockito.Mockito.doNothing;

@Tag(TestTags.USE_CASE)
public class DeleteCouponUseCaseTest extends DeleteCouponUseCaseScenarios {

    @Spy
    @InjectMocks
    private DeleteCouponUseCase deleteCouponUseCase;

    @Mock
    private CouponService couponService;

    @Test
    void testShouldExecuteDeleteCoupon() {
        final DeleteCouponUseCaseBuilder builder = shouldDeleteCoupon();
        final String code = builder.getCode();

        doNothing().when(couponService).deleteByCode(code);

        deleteCouponUseCase.execute(code);
    }
}

