package com.desafio.coupon.api.builder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiErrorBuilder {

    private int status;
    private String message;
    private String path;
}

