package com.desafio.coupon.api.infra.controller.coupon;

import com.desafio.coupon.api.application.coupon.dto.CouponResponseDto;
import com.desafio.coupon.api.application.coupon.dto.CreateCouponRequestDto;
import com.desafio.coupon.api.application.coupon.usecase.CreateCouponUseCase;
import com.desafio.coupon.api.application.coupon.usecase.DeleteCouponUseCase;
import com.desafio.coupon.api.application.coupon.usecase.GetAllCouponsUseCase;
import com.desafio.coupon.api.builder.CouponControllerBuilder;
import com.desafio.coupon.api.commons.test.TestTags;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@Tag(TestTags.CONTROLLER)
public class CouponControllerTest extends CouponControllerScenarios {

    @Spy
    @InjectMocks
    private CouponController couponController;

    @Mock
    private CreateCouponUseCase createCouponUseCase;

    @Mock
    private DeleteCouponUseCase deleteCouponUseCase;

    @Mock
    private GetAllCouponsUseCase getAllCouponsUseCase;

    @Test
    void testShouldGetAllCoupons() {
        final CouponControllerBuilder builder = shouldGetAllCoupons();
        final List<CouponResponseDto> outputCoupons = builder.getOutputList();

        when(getAllCouponsUseCase.execute()).thenReturn(outputCoupons);

        final ResponseEntity<List<CouponResponseDto>> response = couponController.getAll();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals(outputCoupons, response.getBody());
    }

    @Test
    void testShouldGetAllCouponsEmpty() {
        final CouponControllerBuilder builder = shouldGetAllCouponsEmpty();
        final List<CouponResponseDto> outputCoupons = builder.getOutputList();

        when(getAllCouponsUseCase.execute()).thenReturn(outputCoupons);

        final ResponseEntity<List<CouponResponseDto>> response = couponController.getAll();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testShouldCreateCoupon() {
        final CouponControllerBuilder builder = shouldCreateCoupon();
        final CreateCouponRequestDto input = builder.getInput();
        final CouponResponseDto output = builder.getOutput();

        when(createCouponUseCase.execute(input)).thenReturn(output);

        final ResponseEntity<CouponResponseDto> response = couponController.create(input);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(output, response.getBody());
        assertNotNull(response.getBody());
        assertEquals(output.code(), response.getBody().code());
        assertEquals(output.description(), response.getBody().description());
    }

    @Test
    void testShouldDeleteCoupon() {
        final CouponControllerBuilder builder = shouldDeleteCoupon();
        final String codeToDelete = builder.getCodeToDelete();

        doNothing().when(deleteCouponUseCase).execute(codeToDelete);

        final ResponseEntity<Void> response = couponController.delete(codeToDelete);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}

