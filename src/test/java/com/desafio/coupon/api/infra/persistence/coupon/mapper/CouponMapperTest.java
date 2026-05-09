package com.desafio.coupon.api.infra.persistence.coupon.mapper;

import com.desafio.coupon.api.builder.CouponMapperBuilder;
import com.desafio.coupon.api.commons.test.TestTags;
import com.desafio.coupon.api.domain.coupon.model.Coupon;
import com.desafio.coupon.api.infra.persistence.coupon.entity.CouponEntity;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Tag(TestTags.MAPPER)
public class CouponMapperTest extends CouponMapperScenarios {

    @InjectMocks
    private CouponMapper couponMapper;

    @Test
    void testShouldMapEntityToDomain() {
        CouponMapperBuilder builder = shouldMapEntityToDomain();
        CouponEntity input = builder.getEntityInput();

        Coupon output = couponMapper.toDomain(input);

        assertNotNull(output);
        assertEquals(builder.getExpectedDomain().getId(), output.getId());
        assertEquals(builder.getExpectedDomain().getCode(), output.getCode());
        assertEquals(builder.getExpectedDomain().getDescription(), output.getDescription());
        assertEquals(builder.getExpectedDomain().getDiscountValue(), output.getDiscountValue());
        assertEquals(builder.getExpectedDomain().getExpirationDate(), output.getExpirationDate());
        assertEquals(builder.getExpectedDomain().getCreationDate(), output.getCreationDate());
        assertEquals(builder.getExpectedDomain().getDeletionDate(), output.getDeletionDate());
        assertEquals(builder.getExpectedDomain().getStatus(), output.getStatus());
    }

    @Test
    void testShouldMapDomainToEntity() {
        CouponMapperBuilder builder = shouldMapDomainToEntity();
        Coupon input = builder.getDomainInput();

        CouponEntity output = couponMapper.toEntity(input);

        assertNotNull(output);
        assertEquals(builder.getExpectedEntity().getId(), output.getId());
        assertEquals(builder.getExpectedEntity().getCode(), output.getCode());
        assertEquals(builder.getExpectedEntity().getDescription(), output.getDescription());
        assertEquals(builder.getExpectedEntity().getDiscountValue(), output.getDiscountValue());
        assertEquals(builder.getExpectedEntity().getExpirationDate(), output.getExpirationDate());
        assertEquals(builder.getExpectedEntity().getCreationDate(), output.getCreationDate());
        assertEquals(builder.getExpectedEntity().getDeletionDate(), output.getDeletionDate());
        assertEquals(builder.getExpectedEntity().getStatus(), output.getStatus());
    }
}
