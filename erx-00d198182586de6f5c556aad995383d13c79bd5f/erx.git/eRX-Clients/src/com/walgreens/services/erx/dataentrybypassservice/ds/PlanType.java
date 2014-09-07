//
// Generated By:JAX-WS RI IBM 2.2.1-11/25/2013 11:48 AM(foreman)- (JAXB RI IBM 2.2.3-11/25/2013 12:35 PM(foreman)-)
//


package com.walgreens.services.erx.dataentrybypassservice.ds;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PlanType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PlanType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="COMMERCIAL"/>
 *     &lt;enumeration value="COUPON"/>
 *     &lt;enumeration value="MAJOR_MEDICAL"/>
 *     &lt;enumeration value="MEDICAID"/>
 *     &lt;enumeration value="MEDICARE"/>
 *     &lt;enumeration value="UNSPECIFIED"/>
 *     &lt;enumeration value="WORKMANS_COMPENSATION"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
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