package com.walgreens.pharmacy.rules;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ResultType")
@XmlEnum
public enum ResultType {

    COMPLETE,
    MANUAL,
    IN_PROGRESS;

    public String value() {
        return name();
    }

    public static ResultType fromValue(String v) {
        return valueOf(v);
    }

}
