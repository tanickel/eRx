package com.walgreens.pharmacy.rules;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DrugConstraint")
public class DrugConstraint {

	@XmlElement(required = true)
    protected DrugClassType drugClass;
	@XmlElement(required = true)
    protected int maxDaysToFill;
	@XmlElement(required = true)
	// this is a string because sometime the value could be PRN and not -1, or 1,2,3
    protected String totalFillsAllowed;
	
	public DrugConstraint(){}

    /**
     * Gets the value of the drugClass property.
     * 
     * @return
     *     possible object is
     *     {@link DrugClassType }
     *     
     */
    public DrugClassType getDrugClass() {
        return drugClass;
    }

    /**
     * Sets the value of the drugClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrugClassType }
     *     
     */
    public void setDrugClass(DrugClassType value) {
        this.drugClass = value;
    }

    /**
     * Gets the value of the maxDaysToFill property.
     * 
     */
    public int getMaxDaysToFill() {
        return maxDaysToFill;
    }

    /**
     * Sets the value of the maxDaysToFill property.
     * 
     */
    public void setMaxDaysToFill(int value) {
        this.maxDaysToFill = value;
    }


	public String getTotalFillsAllowed() {
		return totalFillsAllowed;
	}

	public void setTotalFillsAllowed(String totalFillAllowed) {
		this.totalFillsAllowed = totalFillAllowed;
	}

}
