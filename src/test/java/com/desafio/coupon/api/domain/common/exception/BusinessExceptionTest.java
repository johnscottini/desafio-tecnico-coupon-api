package com.desafio.coupon.api.domain.common.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BusinessExceptionTest {

    @Test
    void testBusinessExceptionShouldKeepMessage() {
        final String message = "Mensagem teste";
        final BusinessException exception = new TestBusinessException(message);

        assertEquals(message, exception.getMessage());
    }

    private static class TestBusinessException extends BusinessException {
        TestBusinessException(String message) {
            super(message);
        }
    }
}
