package com.desafio.coupon.api.application.coupon.usecase;

import com.desafio.coupon.api.application.coupon.dto.CouponResponseDto;
import com.desafio.coupon.api.application.coupon.dto.CreateCouponRequestDto;
import com.desafio.coupon.api.application.coupon.service.CouponService;
import com.desafio.coupon.api.builder.CreateCouponUseCaseBuilder;
import com.desafio.coupon.api.commons.test.TestTags;
import com.desafio.coupon.api.domain.coupon.model.Coupon;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@Tag(TestTags.USE_CASE)
public class CreateCouponUseCaseTest extends CreateCouponUseCaseScenarios {

    @Spy
    @InjectMocks
    private CreateCouponUseCase createCouponUseCase;

    @Mock
    private CouponService couponService;

    @Test
    void testShouldExecuteCreateCoupon() {
        final CreateCouponUseCaseBuilder builder = shouldCreateCoupon();
        final CreateCouponRequestDto input = builder.getInput();
        final CouponResponseDto expectedOutput = builder.getOutput();

        Coupon coupon = Coupon.reconstitute(
                expectedOutput.id(),
                expectedOutput.code(),
                expectedOutput.description(),
                expectedOutput.discountValue(),
                expectedOutput.expirationDate(),
                expectedOutput.creationDate(),
                expectedOutput.deletionDate(),
                expectedOutput.status()
        );

        when(couponService.create(input.code(), input.description(), input.discountValue(), input.expirationDate()))
                .thenReturn(coupon);

        final CouponResponseDto output = createCouponUseCase.execute(input);

        assertNotNull(output);
        assertEquals(expectedOutput.code(), output.code());
        assertEquals(expectedOutput.description(), output.description());
        assertEquals(expectedOutput.discountValue(), output.discountValue());
        assertEquals(expectedOutput.status(), output.status());
    }
}

