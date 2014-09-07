package com.walgreens.pharmacy.rules;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "DosageFormType")
@XmlEnum
public enum TranslatedDosageFormType {

	CAPSULE, INHALER, LIQUID, OTHER, RECON, TABLET, TOPICAL;

	public String value() {
		return name();
	}

	public static TranslatedDosageFormType fromValue(String v) {
		return valueOf(v);
	}

}
