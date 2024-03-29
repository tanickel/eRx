//
// Generated By:JAX-WS RI IBM 2.2.1-11/25/2013 11:48 AM(foreman)- (JAXB RI IBM 2.2.3-11/25/2013 12:35 PM(foreman)-)
//


package com.walgreens.services.erx.dataentrybypassservice.ds;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DosageFormType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DosageFormType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CAPSULE"/>
 *     &lt;enumeration value="INHALER"/>
 *     &lt;enumeration value="LIQUID"/>
 *     &lt;enumeration value="OTHER"/>
 *     &lt;enumeration value="RECON"/>
 *     &lt;enumeration value="TABLET"/>
 *     &lt;enumeration value="TOPICAL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DosageFormType")
@XmlEnum
public enum DosageFormType {

    CAPSULE,
    INHALER,
    LIQUID,
    OTHER,
    RECON,
    TABLET,
    TOPICAL;

    public String value() {
        return name();
    }

    public static DosageFormType fromValue(String v) {
        return valueOf(v);
    }

}
