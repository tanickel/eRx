package com.walgreens.pharmacy.rules;

public enum SubstitutionIndicator {
	
	ELIGIBLE,
	INELIGBLE;
	
	
    public String value() {
        return name();
    }

    public static SubstitutionIndicator fromValue(String v) {
        return valueOf(v);
    }
    

}
