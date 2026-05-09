package com.desafio.coupon.api.infra.controller.coupon;

import com.desafio.coupon.api.application.coupon.dto.CouponResponseDto;
import com.desafio.coupon.api.application.coupon.dto.CreateCouponRequestDto;
import com.desafio.coupon.api.application.coupon.usecase.CreateCouponUseCase;
import com.desafio.coupon.api.application.coupon.usecase.DeleteCouponUseCase;
import com.desafio.coupon.api.application.coupon.usecase.GetAllCouponsUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/coupons")
@RequiredArgsConstructor
public class CouponController {

	private final CreateCouponUseCase createCouponUseCase;
	private final DeleteCouponUseCase deleteCouponUseCase;
	private final GetAllCouponsUseCase getAllCouponsUseCase;

	@GetMapping
	public ResponseEntity<List<CouponResponseDto>> getAll() {
		List<CouponResponseDto> response = getAllCouponsUseCase.execute();
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<CouponResponseDto> create(@Valid @RequestBody CreateCouponRequestDto request) {
		CouponResponseDto response = createCouponUseCase.execute(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@DeleteMapping("/{code}")
	public ResponseEntity<Void> delete(@Valid @PathVariable String code) {
		deleteCouponUseCase.execute(code);
		return ResponseEntity.noContent().build();
	}
}
