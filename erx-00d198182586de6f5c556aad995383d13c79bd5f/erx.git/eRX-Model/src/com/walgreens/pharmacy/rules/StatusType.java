package com.walgreens.pharmacy.rules;

public enum StatusType {

	    ACTIVE,
	    INACTIVE;

	    public String value() {
	        return name();
	    }

	    public static StatusType fromValue(String v) {
	        return valueOf(v);
	    }

}
