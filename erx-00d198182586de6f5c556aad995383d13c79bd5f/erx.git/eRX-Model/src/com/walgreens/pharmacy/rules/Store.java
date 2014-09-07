package com.walgreens.pharmacy.rules;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Store")
public class Store {

	@XmlElement(required = true)
	protected StateType state;
	@XmlElement(required = true)
	protected String nabpNumber = "";
	@XmlElement
	protected Boolean inOrangeBookState = false;

	public Store(){}
	
	/**
	 * Gets the value of the state property.
	 * 
	 * @return possible object is {@link StateType }
	 * 
	 */
	public StateType getState() {
		return state;
	}

	/**
	 * Sets the value of the state property.
	 * 
	 * @param value
	 *            allowed object is {@link StateType }
	 * 
	 */
	public void setState(StateType value) {
		this.state = value;
	}

	/**
	 * Gets the value of the nabpNumber property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getNabpNumber() {
		return nabpNumber;
	}

	/**
	 * Sets the value of the nabpNumber property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setNabpNumber(String value) {
		this.nabpNumber = value;
	}

	/**
	 * Sets the value of the inOrangeBookState property.
	 * 
	 * @param value
	 *            allowed object is {@link Boolean }
	 * 
	 */
	public void setInOrangeBookState(Boolean value) {
		this.inOrangeBookState = value;
	}

	public Boolean isInOrangeBookState() {
		Boolean ret = inOrangeBookState;
		if (inOrangeBookState == null) {
			ret = new Boolean(false);
		}
		return (ret);
	}
}
