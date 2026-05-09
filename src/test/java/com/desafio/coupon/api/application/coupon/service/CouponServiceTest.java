package com.desafio.coupon.api.application.coupon.service;

import com.desafio.coupon.api.builder.CouponServiceBuilder;
import com.desafio.coupon.api.commons.test.TestTags;
import com.desafio.coupon.api.domain.coupon.model.Coupon;
import com.desafio.coupon.api.domain.coupon.repository.CouponRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Tag(TestTags.SERVICE)
public class CouponServiceTest extends CouponServiceScenarios {

    @Spy
    @InjectMocks
    private CouponService couponService;

    @Mock
    private CouponRepository couponRepository;

    @Test
    void testShouldCreateCoupon() {
        final CouponServiceBuilder builder = shouldCreateCoupon();
        final String code = builder.getCode();
        final String description = builder.getDescription();
        final Coupon output = builder.getOutput();

        when(couponRepository.existsByCodeAndDeletionDateNull("TEST01")).thenReturn(false);
        when(couponRepository.save(any(Coupon.class))).thenReturn(output);

        final Coupon result = couponService.create(code, description, builder.getDiscountValue(), builder.getExpirationDate());

        assertNotNull(result);
        assertEquals(output.getCode(), result.getCode());
        assertEquals(output.getDescription(), result.getDescription());
        assertEquals(output.getDiscountValue(), result.getDiscountValue());
    }

    @Test
    void testShouldDeleteCoupon() {
        final CouponServiceBuilder builder = shouldDeleteCoupon();
        final String code = builder.getCode();
        final Coupon coupon = builder.getOutput();

        when(couponRepository.findByCodeAndDeletionDateNull("00AB12")).thenReturn(Optional.of(coupon));
        when(couponRepository.save(any(Coupon.class))).thenReturn(coupon);

        couponService.deleteByCode(code);

        assertNotNull(coupon);
        assertEquals("00AB12", coupon.getCode());
    }

    @Test
    void testShouldFindAllActive() {
        final CouponServiceBuilder builder = shouldFindAllActive();
        final List<Coupon> outputList = builder.getOutputList();

        when(couponRepository.findAllActive()).thenReturn(outputList);

        final var result = couponService.findAllActive();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(outputList.get(0).getCode(), result.get(0).code());
        assertEquals(outputList.get(1).getCode(), result.get(1).code());
    }

    @Test
    void testShouldFindAllActiveEmpty() {
        final CouponServiceBuilder builder = shouldFindAllActiveEmpty();
        final List<Coupon> outputList = builder.getOutputList();

        when(couponRepository.findAllActive()).thenReturn(outputList);

        final var result = couponService.findAllActive();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}


