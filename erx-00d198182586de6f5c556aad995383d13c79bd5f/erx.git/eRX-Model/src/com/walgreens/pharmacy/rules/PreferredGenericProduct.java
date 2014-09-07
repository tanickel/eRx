package com.walgreens.pharmacy.rules;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PreferredGenericProduct")
public class PreferredGenericProduct {

	@XmlElement(required = true)
    protected String drugId = "";
	@XmlElement(required = true)
    protected boolean controlling = false;
	
	public PreferredGenericProduct(){}

    /**
     * Gets the value of the drugIdentifier property.
     * 
     */
    public String getDrugId() {
        return drugId;
    }

    /**
     * Sets the value of the drugIdentifier property.
     * 
     */
    public void setDrugId(String value) {
        this.drugId = value;
    }

    public boolean isControlling() {
		return controlling;
	}

	public void setControlling(boolean controlling) {
		this.controlling = controlling;
	}

}
