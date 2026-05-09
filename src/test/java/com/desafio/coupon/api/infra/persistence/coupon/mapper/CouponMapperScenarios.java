package com.desafio.coupon.api.infra.persistence.coupon.mapper;

import com.desafio.coupon.api.builder.CouponMapperBuilder;
import com.desafio.coupon.api.database.BaseTest;
import com.desafio.coupon.api.domain.coupon.model.Coupon;
import com.desafio.coupon.api.domain.coupon.model.CouponStatus;
import com.desafio.coupon.api.infra.persistence.coupon.entity.CouponEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CouponMapperScenarios extends BaseTest {

    public CouponMapperBuilder shouldMapEntityToDomain() {
        UUID id = UUID.randomUUID();
        LocalDateTime creationDate = LocalDateTime.now().minusDays(2);
        LocalDateTime expirationDate = LocalDateTime.now().plusDays(10);

        CouponEntity entityInput = new CouponEntity(
                id,
                "00AB12",
                random.nextObject(String.class),
                random.nextObject(BigDecimal.class),
                expirationDate,
                creationDate,
                null,
                CouponStatus.ACTIVE
        );

        Coupon expectedDomain = Coupon.reconstitute(
                id,
                entityInput.getCode(),
                entityInput.getDescription(),
                entityInput.getDiscountValue(),
                entityInput.getExpirationDate(),
                entityInput.getCreationDate(),
                entityInput.getDeletionDate(),
                entityInput.getStatus()
        );

        return CouponMapperBuilder.builder()
                .entityInput(entityInput)
                .expectedDomain(expectedDomain)
                .build();
    }

    public CouponMapperBuilder shouldMapDomainToEntity() {
        UUID id = UUID.randomUUID();
        LocalDateTime creationDate = LocalDateTime.now().minusDays(3);
        LocalDateTime expirationDate = LocalDateTime.now().plusDays(7);

        Coupon domainInput = Coupon.reconstitute(
                id,
                "00CD34",
                random.nextObject(String.class),
                random.nextObject(BigDecimal.class),
                expirationDate,
                creationDate,
                null,
                CouponStatus.ACTIVE
        );

        CouponEntity expectedEntity = new CouponEntity(
                id,
                domainInput.getCode(),
                domainInput.getDescription(),
                domainInput.getDiscountValue(),
                domainInput.getExpirationDate(),
                domainInput.getCreationDate(),
                domainInput.getDeletionDate(),
                domainInput.getStatus()
        );

        return CouponMapperBuilder.builder()
                .domainInput(domainInput)
                .expectedEntity(expectedEntity)
                .build();
    }
}
