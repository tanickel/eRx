//
// Generated By:JAX-WS RI IBM 2.2.1-11/25/2013 11:48 AM(foreman)- (JAXB RI IBM 2.2.3-11/25/2013 12:35 PM(foreman)-)
//


package com.walgreens.services.erx.dataentrybypassservice.ds;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DrugClassType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DrugClassType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="C2"/>
 *     &lt;enumeration value="C3"/>
 *     &lt;enumeration value="C4"/>
 *     &lt;enumeration value="C5"/>
 *     &lt;enumeration value="UNSPECIFIED"/>
 *     &lt;enumeration value="OT"/>
 *     &lt;enumeration value="RX"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DrugClassType")
@XmlEnum
public enum DrugClassType {

    @XmlEnumValue("C2")
    C_2("C2"),
    @XmlEnumValue("C3")
    C_3("C3"),
    @XmlEnumValue("C4")
    C_4("C4"),
    @XmlEnumValue("C5")
    C_5("C5"),
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