package com.desafio.coupon.api.builder;

import com.desafio.coupon.api.domain.coupon.model.Coupon;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class CouponServiceBuilder {

    private String code;
    private String description;
    private BigDecimal discountValue;
    private LocalDateTime expirationDate;
    private Coupon output;
    private List<Coupon> outputList;
}

