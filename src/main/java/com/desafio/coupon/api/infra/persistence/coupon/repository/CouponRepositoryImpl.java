package com.desafio.coupon.api.infra.persistence.coupon.repository;

import com.desafio.coupon.api.domain.coupon.model.Coupon;
import com.desafio.coupon.api.domain.coupon.repository.CouponRepository;
import com.desafio.coupon.api.infra.persistence.coupon.mapper.CouponMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CouponRepositoryImpl implements CouponRepository {

    private final SpringDataCouponJpaRepository springDataCouponJpaRepository;
    private final CouponMapper couponMapper;

    public CouponRepositoryImpl(SpringDataCouponJpaRepository springDataCouponJpaRepository,
                                CouponMapper couponMapper) {
        this.springDataCouponJpaRepository = springDataCouponJpaRepository;
        this.couponMapper = couponMapper;
    }

    @Override
    public Coupon save(Coupon coupon) {
        return couponMapper.toDomain(
            springDataCouponJpaRepository.save(couponMapper.toEntity(coupon))
        );
    }
    
    @Override
    public boolean existsByCodeAndDeletionDateNull(String code) {
        return springDataCouponJpaRepository.existsByCodeAndDeletionDateNull(code);
    }

    @Override
    public Optional<Coupon> findByCodeAndDeletionDateNull(String code) {
        return springDataCouponJpaRepository.findByCodeAndDeletionDateNull(code)
                .map(couponMapper::toDomain);
    }

    @Override
    public List<Coupon> findAllActive() {
        return springDataCouponJpaRepository.findAllByDeletionDateNull()
                .stream()
                .map(couponMapper::toDomain)
                .toList();
    }
}

