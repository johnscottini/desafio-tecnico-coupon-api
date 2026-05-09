package com.desafio.coupon.api.infra.persistence.coupon.repository;

import com.desafio.coupon.api.builder.CouponRepositoryImplBuilder;
import com.desafio.coupon.api.commons.test.TestTags;
import com.desafio.coupon.api.database.RepositoryBaseTest;
import com.desafio.coupon.api.domain.coupon.model.Coupon;
import com.desafio.coupon.api.infra.persistence.coupon.mapper.CouponMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag(TestTags.REPOSITORY)
class CouponRepositoryImplTest extends RepositoryBaseTest {

    @Autowired
    private SpringDataCouponJpaRepository springDataCouponJpaRepository;

    private CouponRepositoryImpl couponRepository;
    private CouponRepositoryScenarios scenarios;

    @BeforeEach
    void setUp() {
        couponRepository = new CouponRepositoryImpl(springDataCouponJpaRepository, new CouponMapper());
        scenarios = new CouponRepositoryScenarios();
        springDataCouponJpaRepository.deleteAll();
    }

    @Test
    void testSaveCoupon() {
        final CouponRepositoryImplBuilder builder = scenarios.shouldSaveCoupon();
        final Coupon couponToSave = builder.getCouponToSave();

        final Coupon output = couponRepository.save(couponToSave);

        assertNotNull(output);
        assertEquals(couponToSave.getId(), output.getId());
        assertEquals(couponToSave.getCode(), output.getCode());
        assertEquals(couponToSave.getDescription(), output.getDescription());
        assertEquals(couponToSave.getDiscountValue(), output.getDiscountValue());
        assertEquals(couponToSave.getStatus(), output.getStatus());
    }

    @Test
    void testExistsByCodeAndDeletionDateNullShouldReturnTrueOnlyForActiveCoupon() {
        final CouponRepositoryImplBuilder builder = scenarios.shouldHaveActiveAndDeletedCoupons();
        final Coupon activeCoupon = builder.getActiveCoupon();
        final Coupon deletedCoupon = builder.getDeletedCoupon();

        couponRepository.save(activeCoupon);
        couponRepository.save(deletedCoupon);

        final boolean activeExists = couponRepository.existsByCodeAndDeletionDateNull(activeCoupon.getCode());
        final boolean deletedExists = couponRepository.existsByCodeAndDeletionDateNull(deletedCoupon.getCode());

        assertTrue(activeExists);
        assertFalse(deletedExists);
    }

    @Test
    void testFindByCodeAndDeletionDateNullShouldReturnOnlyActiveCoupon() {
        final CouponRepositoryImplBuilder builder = scenarios.shouldHaveActiveAndDeletedCoupons();
        final Coupon activeCoupon = builder.getActiveCoupon();
        final Coupon deletedCoupon = builder.getDeletedCoupon();

        couponRepository.save(activeCoupon);
        couponRepository.save(deletedCoupon);

        final Optional<Coupon> activeOutput = couponRepository.findByCodeAndDeletionDateNull(activeCoupon.getCode());
        final Optional<Coupon> deletedOutput = couponRepository.findByCodeAndDeletionDateNull(deletedCoupon.getCode());

        assertTrue(activeOutput.isPresent());
        assertEquals(activeCoupon.getCode(), activeOutput.get().getCode());
        assertTrue(deletedOutput.isEmpty());
    }

    @Test
    void testFindAllActiveShouldReturnOnlyActiveCoupons() {
        final CouponRepositoryImplBuilder builder = scenarios.shouldListOnlyActiveCoupons();
        final List<Coupon> activeCoupons = builder.getActiveCoupons();
        final Coupon deletedCoupon = builder.getDeletedCoupon();

        activeCoupons.forEach(couponRepository::save);
        couponRepository.save(deletedCoupon);

        final List<Coupon> output = couponRepository.findAllActive();

        assertNotNull(output);
        assertEquals(2, output.size());
        assertTrue(output.stream().allMatch(coupon -> coupon.getDeletionDate() == null));
        assertTrue(output.stream().anyMatch(coupon -> coupon.getCode().equals(activeCoupons.get(0).getCode())));
        assertTrue(output.stream().anyMatch(coupon -> coupon.getCode().equals(activeCoupons.get(1).getCode())));
        assertFalse(output.stream().anyMatch(coupon -> coupon.getCode().equals(deletedCoupon.getCode())));
    }
}
