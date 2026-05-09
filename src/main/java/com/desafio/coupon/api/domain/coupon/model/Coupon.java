package com.desafio.coupon.api.domain.coupon.model;

import com.desafio.coupon.api.domain.coupon.exception.CouponAlreadyDeletedException;
import com.desafio.coupon.api.domain.coupon.exception.InvalidCouponCodeException;
import com.desafio.coupon.api.domain.coupon.exception.InvalidCouponDiscountValueException;
import com.desafio.coupon.api.domain.coupon.exception.InvalidCouponExpirationDateException;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
public class Coupon {

    private static final int DEFAULT_CODE_LENGTH = 6;
    private static final BigDecimal DEFAULT_MINIMUM_DISCOUNT_VALUE =
            BigDecimal.valueOf(0.5);

    private final UUID id;
    private final String code;
    private final String description;
    private final BigDecimal discountValue;
    private final LocalDateTime expirationDate;
    private final LocalDateTime creationDate;
    private LocalDateTime deletionDate;
    private CouponStatus status;

    private Coupon(UUID id, String code, String description, BigDecimal discountValue,
                   LocalDateTime expirationDate, LocalDateTime creationDate, LocalDateTime deletionDate,
                   CouponStatus status) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.discountValue = discountValue;
        this.expirationDate = expirationDate;
        this.creationDate = creationDate;
        this.deletionDate = deletionDate;
        this.status = status;
    }

    public static Coupon create(String code, String description, BigDecimal discountValue,
                               LocalDateTime expirationDate) {
        String normalizedCode = normalize(code);
        validateDiscountValue(discountValue);
        validateExpirationDate(expirationDate);
        
        return new Coupon(
            UUID.randomUUID(),
            normalizedCode,
            description,
            discountValue,
            expirationDate,
            LocalDateTime.now(),
            null,
            CouponStatus.ACTIVE
        );
    }

    public static Coupon reconstitute(UUID id, String code, String description, BigDecimal discountValue,
                                      LocalDateTime expirationDate, LocalDateTime creationDate,
                                      LocalDateTime deletionDate, CouponStatus status) {
        return new Coupon(
            id,
            code,
            description,
            discountValue,
            expirationDate,
            creationDate,
            deletionDate,
            status
        );
    }

    public static String normalize(String code) {
        return normalizeCode(code);
    }

    public void delete() {
        if (Objects.equals(this.status, CouponStatus.DELETED)) {
            throw new CouponAlreadyDeletedException(this.code);
        }
        this.status = CouponStatus.DELETED;
        this.deletionDate = LocalDateTime.now();
    }

    private static String normalizeCode(String code) {
        if (Strings.isBlank(code)) {
            throw new InvalidCouponCodeException();
        }

        String normalizedCode = code.replaceAll("[^a-zA-Z0-9]", "");

        if (normalizedCode.length() > DEFAULT_CODE_LENGTH) {
            throw new InvalidCouponCodeException();
        }

        return leftPadWithZeros(normalizedCode);
    }

    private static String leftPadWithZeros(String value) {
        return String.format(
                "%" + DEFAULT_CODE_LENGTH + "s",
                value
        ).replace(' ', '0');
    }

    private static void validateDiscountValue(BigDecimal discountValue) {
        if (Objects.isNull(discountValue) || discountValue.compareTo(DEFAULT_MINIMUM_DISCOUNT_VALUE) < 0) {
            throw new InvalidCouponDiscountValueException();
        }
    }

    private static void validateExpirationDate(LocalDateTime expirationDate) {
        if (Objects.isNull(expirationDate) || isExpired(expirationDate)) {
            throw new InvalidCouponExpirationDateException();
        }
    }

    private static boolean isExpired(LocalDateTime expirationDate) {
        return expirationDate.isBefore(LocalDateTime.now());
    }
}
