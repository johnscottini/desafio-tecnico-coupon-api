package com.desafio.coupon.api.infra.persistence.coupon.repository;

import com.desafio.coupon.api.infra.persistence.coupon.entity.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataCouponJpaRepository extends JpaRepository<CouponEntity, UUID> {

    boolean existsByCodeAndDeletionDateNull(String code);

    Optional<CouponEntity> findByCodeAndDeletionDateNull(String code);
    
    List<CouponEntity> findAllByDeletionDateNull();
}

