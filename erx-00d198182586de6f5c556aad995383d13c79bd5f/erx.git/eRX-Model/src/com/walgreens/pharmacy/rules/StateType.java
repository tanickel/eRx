package com.walgreens.pharmacy.rules;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "StateType")
@XmlEnum
public enum StateType {

	AK, AL, AR, AZ, CA, CO, CT, DC, DE, FL, GA, GU, HI, IA, ID, IL, IN, 
	KS, KY, LA, MA, MD, ME, MI, MN, MO, MS, MT, NC, ND, NE, NH, NJ, NM, NV, 
	NY, OH, OK, OR, PA, PR, RI, SC, SD, TN, TX, UT, VA, VI, VT, WA, WI, WV, 
	WY, UNSPECIFIED;

	public String value() {
		return name();
	}

	public static StateType fromValue(String v) {
		return valueOf(v);
	}

}
