package com.desafio.coupon.api.application.coupon.usecase;

import com.desafio.coupon.api.application.coupon.dto.CouponResponseDto;
import com.desafio.coupon.api.application.coupon.dto.CreateCouponRequestDto;
import com.desafio.coupon.api.application.coupon.service.CouponService;
import com.desafio.coupon.api.domain.coupon.model.Coupon;
import org.springframework.stereotype.Service;

@Service
public class CreateCouponUseCase {

	private final CouponService couponService;

	public CreateCouponUseCase(CouponService couponService) {
		this.couponService = couponService;
	}

	public CouponResponseDto execute(CreateCouponRequestDto request) {
		Coupon createdCoupon = couponService.create(
			request.code(),
			request.description(),
			request.discountValue(),
			request.expirationDate()
		);

		return CouponResponseDto.fromDomain(createdCoupon);
	}
}

