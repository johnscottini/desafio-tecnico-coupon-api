package com.desafio.coupon.api.application.coupon.usecase;

import com.desafio.coupon.api.application.coupon.dto.CouponResponseDto;
import com.desafio.coupon.api.builder.GetAllCouponsUseCaseBuilder;
import com.desafio.coupon.api.commons.test.TestTags;
import com.desafio.coupon.api.application.coupon.service.CouponService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@Tag(TestTags.USE_CASE)
public class GetAllCouponsUseCaseTest extends GetAllCouponsUseCaseScenarios {

    @Spy
    @InjectMocks
    private GetAllCouponsUseCase getAllCouponsUseCase;

    @Mock
    private CouponService couponService;

    @Test
    void testShouldExecuteGetAllCoupons() {
        final GetAllCouponsUseCaseBuilder builder = shouldGetAllCoupons();
        final List<CouponResponseDto> expectedOutput = builder.getOutput();

        when(couponService.findAllActive()).thenReturn(expectedOutput);

        final List<CouponResponseDto> output = getAllCouponsUseCase.execute();

        assertNotNull(output);
        assertEquals(2, output.size());
        assertEquals(expectedOutput, output);
    }

    @Test
    void testShouldExecuteGetAllCouponsEmpty() {
        final GetAllCouponsUseCaseBuilder builder = shouldGetAllCouponsEmpty();
        final List<CouponResponseDto> expectedOutput = builder.getOutput();

        when(couponService.findAllActive()).thenReturn(expectedOutput);

        final List<CouponResponseDto> output = getAllCouponsUseCase.execute();

        assertNotNull(output);
        assertTrue(output.isEmpty());
    }
}

