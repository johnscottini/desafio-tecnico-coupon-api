package com.desafio.coupon.api.domain.coupon.repository;

import com.desafio.coupon.api.domain.coupon.model.Coupon;

import java.util.List;
import java.util.Optional;

public interface CouponRepository {

    Coupon save(Coupon coupon);

    boolean existsByCodeAndDeletionDateNull(String code);

    Optional<Coupon> findByCodeAndDeletionDateNull(String normalizedCode);
    
    List<Coupon> findAllActive();
}

