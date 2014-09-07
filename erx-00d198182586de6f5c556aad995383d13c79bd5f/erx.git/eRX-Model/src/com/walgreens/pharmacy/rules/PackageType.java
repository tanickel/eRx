package com.walgreens.pharmacy.rules;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "PackageType")
@XmlEnum
public enum PackageType {

    STANDARD,
    UNIT_OF_USE,
    UNIT_DOSE,
    UNSPECIFIED;

    public String value() {
        return name();
    }

    public static PackageType fromValue(String v) {
        return valueOf(v);
    }

}
