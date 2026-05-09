package com.desafio.coupon.api.application.coupon.usecase;

import com.desafio.coupon.api.application.coupon.service.CouponService;
import org.springframework.stereotype.Service;

@Service
public class DeleteCouponUseCase {

	private final CouponService couponService;

	public DeleteCouponUseCase(CouponService couponService) {
		this.couponService = couponService;
	}

	public void execute(String code) {
		couponService.deleteByCode(code);
	}
}

