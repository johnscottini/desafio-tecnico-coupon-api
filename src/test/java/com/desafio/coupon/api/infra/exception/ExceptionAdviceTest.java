package com.desafio.coupon.api.infra.exception;

import com.desafio.coupon.api.builder.ExceptionAdviceBuilder;
import com.desafio.coupon.api.domain.common.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ExceptionAdviceTest extends ExceptionAdviceScenarios {

    private final ExceptionAdvice exceptionAdvice = new ExceptionAdvice();

    @Mock
    private HttpServletRequest request;

    @Test
    void testShouldHandleBusinessException() {
        final ExceptionAdviceBuilder builder = shouldHandleBusinessException();
        final BusinessException exception = new TestBusinessException(builder.getBusinessMessage());

        when(request.getRequestURI()).thenReturn(builder.getPath());

        final ResponseEntity<ApiError> response = exceptionAdvice.handleBusinessException(exception, request);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode().value());
        assertEquals(builder.getBusinessMessage(), response.getBody().getMessage());
        assertEquals(builder.getPath(), response.getBody().getPath());
    }

    @Test
    void testShouldHandleGenericException() {
        final ExceptionAdviceBuilder builder = shouldHandleGenericException();
        final Exception exception = new Exception("Erro generico");

        when(request.getRequestURI()).thenReturn(builder.getPath());

        final ResponseEntity<ApiError> response = exceptionAdvice.handleGenericException(exception, request);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatusCode().value());
        assertEquals(builder.getGenericMessage(), response.getBody().getMessage());
        assertEquals(builder.getPath(), response.getBody().getPath());
    }

    @Test
    void testShouldHandleMethodArgumentNotValid() throws Exception {
        final ExceptionAdviceBuilder builder = shouldHandleValidationException();

        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(new DummyController())
                .setControllerAdvice(exceptionAdvice)
                .build();

        mockMvc.perform(post(builder.getPath())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"code\":\"\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(builder.getValidationMessage()))
                .andExpect(jsonPath("$.path").value(builder.getPath()));
    }

    @RestController
    private static class DummyController {

        @PostMapping("/api/coupons")
        ResponseEntity<Void> validateInput(@Valid @RequestBody DummyInput input) {
            return ResponseEntity.ok().build();
        }
    }

    private record DummyInput(@NotBlank(message = "code é obrigatório") String code) {
    }

    private static class TestBusinessException extends BusinessException {
        private TestBusinessException(String message) {
            super(message);
        }
    }
}
