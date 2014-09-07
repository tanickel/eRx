package com.walgreens.pharmacy.rules;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.walgreens.pharmacy.rules.util.DateXmlAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CalculatedValues")
public class CalculatedValues {

	@XmlElement
	protected String refillsRemainingEntered = "";
	@XmlElement(required = true)
	protected int dailyDose;
	@XmlElement
	protected int daysOfSupply; //decide if we need later
	@XmlElement(required = true)
	@XmlJavaTypeAdapter(DateXmlAdapter.class)
	@XmlSchemaType(name = "date")
	protected Date expirationDate;
	@XmlElement(required = true)
	protected int maxDaysToFill;
	@XmlElement(required = true)
	@XmlJavaTypeAdapter(DateXmlAdapter.class)
	@XmlSchemaType(name = "date")
	protected Date refillBeforeDate;
	@XmlElement(required = true)
	protected String refillsRemaining = "";
	@XmlElement(required = true)
	protected double quantity;
	@XmlElement(required = true)
	protected double quantityDispensed;
	@XmlElement(required = true)
	protected String quantityRemaining;
	@XmlElement(required = true)
	protected Integer numberOfFillsPrescribed; //Number of refills prescribed plus 1
	@XmlElement(required = true)
	protected int totalDefaultDaysOfSupply; //added 08-06-2014

	public CalculatedValues(){}
	
	/**
	 * Gets the value of the dailyDose property.
	 * 
	 */
	public int getDailyDose() {
		return dailyDose;
	}

	/**
	 * Sets the value of the dailyDose property.
	 * 
	 */
	public void setDailyDose(int value) {
		this.dailyDose = value;
	}

	/**
	 * Gets the value of the daysSupply property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public int getDaysOfSupply() {
		return this.daysOfSupply;
	}

	/**
	 * Sets the value of the daysSupply property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setDaysOfSupply(int value) {
		this.daysOfSupply = value;
	}

	/**
	 * Gets the value of the expirationDate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public Date getExpirationDate() {
		return expirationDate;
	}

	/**
	 * Sets the value of the expirationDate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setExpirationDate(Date value) {
		this.expirationDate = value;
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
	
	/**
	 * Gets the value of the refillBeforeDate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public Date getRefillBeforeDate() {
		return refillBeforeDate;
	}

	/**
	 * Sets the value of the refillBeforeDate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRefillBeforeDate(Date value) {
		this.refillBeforeDate = value;
	}

	/**
	 * Gets the value of the refillsRemaining property.
	 * 
	 */
	public String getRefillsRemaining() {
		return refillsRemaining;
	}

	/**
	 * Sets the value of the refillsRemaining property.
	 * 
	 */
	public void setRefillsRemaining(String value) {
		this.refillsRemaining = value;
	}

	/**
	 * Gets the value of the quantity property.
	 * 
	 */
	public double getQuantity() {
		return quantity;
	}

	/**
	 * Sets the value of the quantity property.
	 * 
	 */
	public void setQuantity(double value) {
		this.quantity = value;
	}

	/**
	 * Gets the value of the quantityDispensed property.
	 * 
	 */
	public double getQuantityDispensed() {
		return quantityDispensed;
	}

	/**
	 * Sets the value of the quantityDispensed property.
	 * 
	 */
	public void setQuantityDispensed(double value) {
		this.quantityDispensed = value;
	}

	/**
	 * Gets the value of the quantityRemaining property.
	 * 
	 */
	public String getQuantityRemaining() {
		return quantityRemaining;
	}

	/**
	 * Sets the value of the quantityRemaining property.
	 * 
	 */
	public void setQuantityRemaining(String value) {
		this.quantityRemaining = value;
	}

	

	/**
	 * Sets the value of the fillNumberPrescribed property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setNumberOfFillsPrescribed(Integer value) {
		this.numberOfFillsPrescribed = value;
	}

	public Integer getNumberOfFillsPrescribed() {
		Integer ret = numberOfFillsPrescribed;
		if (numberOfFillsPrescribed == null) {
			ret = new Integer(0);
		}
		return (ret);
	}
	
	/**
	 * Gets the value of the TotalDefaultDaysOfSupply property.
	 * 
	 */
	public int getTotalDefaultDaysOfSupply() {
		return totalDefaultDaysOfSupply;
	}

	/**
	 * Sets the value of the TotalDefaultDaysOfSupply property.
	 * 
	 */
	public void setTotalDefaultDaysOfSupply(int value) {
		this.totalDefaultDaysOfSupply = value;
	}

	public String getRefillsRemainingEntered() {
		return refillsRemainingEntered;
	}

	public void setRefillsRemainingEntered(String refillsRemainingEntered) {
		this.refillsRemainingEntered = refillsRemainingEntered;
	}
}
