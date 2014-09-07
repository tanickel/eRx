package com.walgreens.pharmacy.rules;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "PaymentMethodType")
@XmlEnum
public enum PaymentMethodType {

    CASH,
    OTHER,
    UNSPECIFIED,
    THIRD_PARTY_PLAN;

    public String value() {
        return name();
    }

    public static PaymentMethodType fromValue(String v) {
        return valueOf(v);
    }

}
