package com.walgreens.pharmacy.rules;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "PlanType")
@XmlEnum
public enum PlanType {

    COMMERCIAL,
    COUPON,
    MAJOR_MEDICAL,
    MEDICAID,
    MEDICARE,
    UNSPECIFIED,
    WORKMANS_COMPENSATION;

    public String value() {
        return name();
    }

    public static PlanType fromValue(String v) {
        return valueOf(v);
    }

}
