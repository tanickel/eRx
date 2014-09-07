package com.walgreens.pharmacy.rules;

public enum PaymentModeType {

    COB,
    COUPON,
    DISCOUNT_CARD,
    PRIMARY,
    UNSPECIFIED;

    public String value() {
        return name();
    }

    public static PaymentModeType fromValue(String v) {
        return valueOf(v);
    }

}
