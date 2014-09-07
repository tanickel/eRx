package com.walgreens.pharmacy.rules;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "CategoryType")
@XmlEnum
public enum CategoryType {
	Controlling,
	StorePreferred,
	Warehouse;
	
    public String value() {
        return name();
    }

    public static CategoryType fromValue(String v) {
        return valueOf(v);
    }

}
