package com.desafio.coupon.api.infra.controller.coupon;

import com.desafio.coupon.api.application.coupon.dto.CouponResponseDto;
import com.desafio.coupon.api.application.coupon.dto.CreateCouponRequestDto;
import com.desafio.coupon.api.application.coupon.usecase.CreateCouponUseCase;
import com.desafio.coupon.api.application.coupon.usecase.DeleteCouponUseCase;
import com.desafio.coupon.api.application.coupon.usecase.GetAllCouponsUseCase;
import com.desafio.coupon.api.infra.exception.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Coupons", description = "API para gerenciar cupons")
public class CouponController {

	private final CreateCouponUseCase createCouponUseCase;
	private final DeleteCouponUseCase deleteCouponUseCase;
	private final GetAllCouponsUseCase getAllCouponsUseCase;

	@GetMapping
	@Operation(summary = "Listar cupons ativos")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Cupons retornados com sucesso", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CouponResponseDto.class)))),
		@ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema(implementation = ApiError.class)))
	})
	public ResponseEntity<List<CouponResponseDto>> getAll() {
		List<CouponResponseDto> response = getAllCouponsUseCase.execute();
		return ResponseEntity.ok(response);
	}

	@PostMapping
	@Operation(summary = "Criar cupom")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Cupom criado com sucesso", content = @Content(schema = @Schema(implementation = CouponResponseDto.class))),
		@ApiResponse(responseCode = "400", description = "Dados invalidos ou regra de negocio violada", content = @Content(schema = @Schema(implementation = ApiError.class))),
		@ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema(implementation = ApiError.class)))
	})
	public ResponseEntity<CouponResponseDto> create(@Valid @RequestBody CreateCouponRequestDto request) {
		CouponResponseDto response = createCouponUseCase.execute(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@DeleteMapping("/{code}")
	@Operation(summary = "Deletar cupom por codigo")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Cupom deletado com sucesso"),
		@ApiResponse(responseCode = "400", description = "Requisicao invalida", content = @Content(schema = @Schema(implementation = ApiError.class))),
		@ApiResponse(responseCode = "404", description = "Cupom nao encontrado", content = @Content(schema = @Schema(implementation = ApiError.class))),
		@ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema(implementation = ApiError.class)))
	})
	public ResponseEntity<Void> delete(@Valid @PathVariable String code) {
		deleteCouponUseCase.execute(code);
		return ResponseEntity.noContent().build();
	}
}
