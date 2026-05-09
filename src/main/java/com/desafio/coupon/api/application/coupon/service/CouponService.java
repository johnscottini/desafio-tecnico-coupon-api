package com.desafio.coupon.api.application.coupon.service;

import com.desafio.coupon.api.application.coupon.dto.CouponResponseDto;
import com.desafio.coupon.api.domain.coupon.exception.CouponAlreadyExistsException;
import com.desafio.coupon.api.domain.coupon.exception.CouponNotFoundException;
import com.desafio.coupon.api.domain.coupon.model.Coupon;
import com.desafio.coupon.api.domain.coupon.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {

	private final CouponRepository couponRepository;

	public Coupon create(String code, String description, BigDecimal discountValue,
						 LocalDateTime expirationDate) {
        String normalizedCode = Coupon.normalize(code);
        if (couponRepository.existsByCodeAndDeletionDateNull(normalizedCode)) {
            throw new CouponAlreadyExistsException(normalizedCode);
        }

        Coupon coupon = Coupon.create(code, description, discountValue, expirationDate);
		return couponRepository.save(coupon);
	}

	public void deleteByCode(String code) {
		String normalizedCode = Coupon.normalize(code);

		Coupon coupon = couponRepository.findByCodeAndDeletionDateNull(normalizedCode)
			.orElseThrow(() -> new CouponNotFoundException(normalizedCode));

		coupon.delete();
		couponRepository.save(coupon);
	}

	public List<CouponResponseDto> findAllActive() {
		return couponRepository.findAllActive()
			.stream()
			.map(CouponResponseDto::fromDomain)
			.toList();
	}
}


