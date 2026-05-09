package com.desafio.coupon.api.domain.coupon.model;

public enum CouponStatus {
    ACTIVE("Ativo"),
    INACTIVE("Inativo"),
    EXPIRED("Expirado"),
    DELETED("Deletado");

    private final String descricao;

    CouponStatus(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

