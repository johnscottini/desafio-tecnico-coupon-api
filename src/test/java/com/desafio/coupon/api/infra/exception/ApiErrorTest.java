package com.desafio.coupon.api.infra.exception;

import com.desafio.coupon.api.builder.ApiErrorBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ApiErrorTest extends ApiErrorScenarios {

    @Test
    void testShouldCreateApiError() {
        final ApiErrorBuilder builder = shouldCreateApiError();

        final ApiError apiError = new ApiError(
                builder.getStatus(),
                builder.getMessage(),
                builder.getPath()
        );

        assertEquals(builder.getStatus(), apiError.getStatus());
        assertEquals(builder.getMessage(), apiError.getMessage());
        assertEquals(builder.getPath(), apiError.getPath());
        assertNotNull(apiError.getTimestamp());
    }
}

