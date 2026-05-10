package com.desafio.coupon.api.domain.coupon.model;

import com.desafio.coupon.api.builder.CouponModelBuilder;
import com.desafio.coupon.api.domain.coupon.exception.CouponAlreadyDeletedException;
import com.desafio.coupon.api.domain.coupon.exception.InvalidCouponCodeException;
import com.desafio.coupon.api.domain.coupon.exception.InvalidCouponDiscountValueException;
import com.desafio.coupon.api.domain.coupon.exception.InvalidCouponExpirationDateException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CouponTest extends CouponScenarios {

    @Test
    void testShouldCreateCouponWithNormalizedCode() {
        final CouponModelBuilder builder = shouldCreateCouponWithNormalizedCode();

        final Coupon output = Coupon.create(
                builder.getRawCode(),
                builder.getDescription(),
                builder.getDiscountValue(),
                builder.getExpirationDate()
        );

        assertNotNull(output.getId());
        assertEquals(builder.getNormalizedCode(), output.getCode());
        assertEquals(builder.getDescription(), output.getDescription());
        assertEquals(builder.getDiscountValue(), output.getDiscountValue());
        assertEquals(CouponStatus.ACTIVE, output.getStatus());
    }

    @Test
    void testShouldNormalizeCodeWithOnlySymbols() {
        final CouponModelBuilder builder = shouldNormalizeCodeWithOnlySymbols();

        final String output = Coupon.normalize(builder.getRawCode());

        assertEquals(builder.getNormalizedCode(), output);
    }

    @Test
    void testShouldThrowInvalidCodeByLength() {
        final CouponModelBuilder builder = shouldThrowInvalidCodeByLength();

        assertThrows(InvalidCouponCodeException.class, () -> Coupon.normalize(builder.getRawCode()));
    }

    @Test
    void testShouldThrowInvalidDiscountByNull() {
        final CouponModelBuilder builder = shouldThrowInvalidDiscountByNull();

        assertThrows(InvalidCouponDiscountValueException.class, () -> Coupon.create(
                builder.getRawCode(),
                builder.getDescription(),
                builder.getDiscountValue(),
                builder.getExpirationDate()
        ));
    }

    @Test
    void testShouldThrowInvalidDiscountByMinimumRule() {
        final CouponModelBuilder builder = shouldThrowInvalidDiscountByMinimumRule();

        assertThrows(InvalidCouponDiscountValueException.class, () -> Coupon.create(
                builder.getRawCode(),
                builder.getDescription(),
                builder.getDiscountValue(),
                builder.getExpirationDate()
        ));
    }

    @Test
    void testShouldThrowInvalidExpirationByNull() {
        final CouponModelBuilder builder = shouldThrowInvalidExpirationByNull();

        assertThrows(InvalidCouponExpirationDateException.class, () -> Coupon.create(
                builder.getRawCode(),
                builder.getDescription(),
                builder.getDiscountValue(),
                builder.getExpirationDate()
        ));
    }

    @Test
    void testShouldThrowInvalidExpirationByPastDate() {
        final CouponModelBuilder builder = shouldThrowInvalidExpirationByPastDate();

        assertThrows(InvalidCouponExpirationDateException.class, () -> Coupon.create(
                builder.getRawCode(),
                builder.getDescription(),
                builder.getDiscountValue(),
                builder.getExpirationDate()
        ));
    }

    @Test
    void testShouldDeleteActiveCoupon() {
        final CouponModelBuilder builder = shouldDeleteActiveCoupon();
        final Coupon coupon = builder.getCoupon();

        coupon.delete();

        assertEquals(CouponStatus.DELETED, coupon.getStatus());
        assertNotNull(coupon.getDeletionDate());
    }

    @Test
    void testShouldThrowAlreadyDeletedCoupon() {
        final CouponModelBuilder builder = shouldThrowAlreadyDeletedCoupon();
        final Coupon coupon = builder.getCoupon();

        assertThrows(CouponAlreadyDeletedException.class, coupon::delete);
    }

    @Test
    void testShouldReconstituteCoupon() {
        final CouponModelBuilder builder = shouldReconstituteCoupon();

        final Coupon output = Coupon.reconstitute(
                builder.getId(),
                builder.getNormalizedCode(),
                builder.getDescription(),
                builder.getDiscountValue(),
                builder.getExpirationDate(),
                builder.getCreationDate(),
                builder.getDeletionDate(),
                builder.getStatus()
        );

        assertEquals(builder.getId(), output.getId());
        assertEquals(builder.getNormalizedCode(), output.getCode());
        assertEquals(builder.getDescription(), output.getDescription());
        assertEquals(builder.getDiscountValue(), output.getDiscountValue());
        assertEquals(builder.getExpirationDate(), output.getExpirationDate());
        assertEquals(builder.getCreationDate(), output.getCreationDate());
        assertEquals(builder.getDeletionDate(), output.getDeletionDate());
        assertEquals(builder.getStatus(), output.getStatus());
    }
}

