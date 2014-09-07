package com.walgreens.pharmacy.rules;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "DrugClassType")
@XmlEnum
/**
 * 
 * 
 *
 */
public enum DrugClassType {

    @XmlEnumValue("C2")
    C2("C2"),
    @XmlEnumValue("C3")
    C3("C3"),
    @XmlEnumValue("C4")
    C4("C4"),
    @XmlEnumValue("C5")
    C5("C5"),
    UNSPECIFIED("UNSPECIFIED"),
    OT("OT"),
    RX("RX");
    private final String value;

    DrugClassType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DrugClassType fromValue(String v) {
        for (DrugClassType c: DrugClassType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
