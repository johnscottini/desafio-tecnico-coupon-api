package com.desafio.coupon.api.infra.persistence.coupon.mapper;

import com.desafio.coupon.api.domain.coupon.model.Coupon;
import com.desafio.coupon.api.infra.persistence.coupon.entity.CouponEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CouponMapper {


    public Coupon toDomain(CouponEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }

        return Coupon.reconstitute(
            entity.getId(),
            entity.getCode(),
            entity.getDescription(),
            entity.getDiscountValue(),
            entity.getExpirationDate(),
            entity.getCreationDate(),
            entity.getDeletionDate(),
            entity.getStatus()
        );
    }

    public CouponEntity toEntity(Coupon coupon) {
        if (Objects.isNull(coupon)) {
            return null;
        }
        
        return new CouponEntity(
            coupon.getId(),
            coupon.getCode(),
            coupon.getDescription(),
            coupon.getDiscountValue(),
            coupon.getExpirationDate(),
            coupon.getCreationDate(),
            coupon.getDeletionDate(),
            coupon.getStatus()
        );
    }
}
