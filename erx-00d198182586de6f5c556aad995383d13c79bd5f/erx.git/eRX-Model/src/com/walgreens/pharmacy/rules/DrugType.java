package com.walgreens.pharmacy.rules;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "DrugType")
@XmlEnum
public enum DrugType {

    BRAND,
    GENERIC,
    UNSPECIFIED;

    public String value() {
        return name();
    }

    public static DrugType fromValue(String v) {
        return valueOf(v);
    }

}
