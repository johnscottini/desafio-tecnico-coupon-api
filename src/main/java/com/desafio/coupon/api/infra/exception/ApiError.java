package com.desafio.coupon.api.infra.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Schema(description = "Resposta de erro padronizada da API")
public class ApiError {

    @Schema(description = "Código HTTP do erro", example = "400")
    private final int status;

    @Schema(description = "Mensagem de erro", example = "Coupon já foi deletado")
    private final String message;

    @Schema(description = "Timestamp do erro", example = "2025-05-09T10:30:00")
    private final LocalDateTime timestamp;

    @Schema(description = "Caminho da requisição", example = "/api/coupons/delete")
    private final String path;

    public ApiError(int status, String message, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }
}

