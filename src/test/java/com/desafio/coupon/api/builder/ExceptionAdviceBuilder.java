package com.desafio.coupon.api.builder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionAdviceBuilder {

    private String businessMessage;
    private String genericMessage;
    private String validationMessage;
    private String path;
}

