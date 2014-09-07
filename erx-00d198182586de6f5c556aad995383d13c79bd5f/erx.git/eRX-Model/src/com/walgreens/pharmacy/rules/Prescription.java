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
@XmlType(name = "Prescription")
/**
 * Represents the doctor's prescription for the patient.
*/
public class Prescription {
	
	@XmlElement
	protected String refillQualifier = "";
	@XmlElement
	protected Drug transmittedDrug;
	@XmlElement
	protected Sig sig;
	@XmlElement
	protected Fill fill;
	@XmlElement
	protected String ncltCode = "";
	// this is a major input to search for the Ndc in the substitution drug list so it can get the drug instance
	@XmlElement
	protected String transmittedNdc = "";
	@XmlElement
	protected int dispensedAsWritten;
	@XmlElement
	protected Boolean doNotFill = false;
	@XmlElement
	protected String notes = "";
	@XmlElement(required = true)
	protected int numberOfRefillsPrescribed;
	@XmlElement(required = true)
	@XmlJavaTypeAdapter(DateXmlAdapter.class)
	@XmlSchemaType(name = "date")
	protected Date originalDate;
	@XmlElement(required = true)
	protected double quantityPrescribed;
	@XmlElement
	protected double transformedQuantityPrescribed;
	@XmlElement(required = true)
	@XmlJavaTypeAdapter(DateXmlAdapter.class)
	@XmlSchemaType(name = "date")
	protected Date refillBeforeDate;
	@XmlElement
	protected Boolean useAsDirected = false;
	@XmlElement(required = true)
	protected Store store;
	
	public Prescription(){}
	

	public String getNcltCode() {
		return ncltCode;
	}

	public void setNcltCode(String ncltCode) {
		this.ncltCode = ncltCode;
	}

	/**
	 * Gets the value of the currentFill property.
	 * 
	 * @return possible object is {@link Fill }
	 * 
	 */
	public Fill getFill() {
		return fill;
	}

	/**
	 * Sets the value of the currentFill property.
	 * 
	 * @param value
	 *            allowed object is {@link Fill }
	 * 
	 */
	public void setFill(Fill value) {
		this.fill = value;
	}


	/**
	 * Sets the value of the doNotFill property.
	 * 
	 * @param value
	 *            allowed object is {@link Boolean }
	 * 
	 */
	public void setDoNotFill(Boolean value) {
		this.doNotFill = value;
	}

	/**
	 * Sets the value of the notes property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setNotes(String value) {
		this.notes = value;
	}

	/**
	 * Gets the value of the numberOfRefills property.
	 * 
	 */
	public int getNumberOfRefillsPrescribed() {
		return numberOfRefillsPrescribed;
	}

	/**
	 * Sets the value of the numberOfRefills property.
	 * 
	 */
	public void setNumberOfRefillsPrescribed(int value) {
		this.numberOfRefillsPrescribed = value;
	}

	/**
	 * Gets the value of the originalDate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public Date getOriginalDate() {
		return originalDate;
	}

	/**
	 * Sets the value of the originalDate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOriginalDate(Date value) {
		this.originalDate = value;
	}

	/**
	 * Gets the value of the quantityPrescribed property.
	 * 
	 */
	public double getQuantityPrescribed() {
		return quantityPrescribed;

	}

	/**
	 * Sets the value of the quantityPrescribed property.
	 * 
	 */
	public void setQuantityPrescribed(double value) {
		this.quantityPrescribed = value;
	}

	public String getTransmittedNdc() {
		return transmittedNdc;
	}

	public void setTransmittedNdc(String transmittedNdc) {
		this.transmittedNdc = transmittedNdc;
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
	 * Sets the value of the useAsDirected property.
	 * 
	 * @param value
	 *            allowed object is {@link Boolean }
	 * 
	 */
	public void setUseAsDirected(Boolean value) {
		this.useAsDirected = value;
	}



	public Boolean isDoNotFill() {
		Boolean ret = doNotFill;
		if (doNotFill == null) {
			ret = new Boolean(false);
		}
		return (ret);
	}

	public String getNotes() {
		String ret = notes;
		if (notes == null) {
			ret = new String();
		}
		return (ret);
	}

	public Boolean isUseAsDirected() {
		Boolean ret = useAsDirected;
		if (useAsDirected == null) {
			ret = new Boolean(false);
		}
		return (ret);
	}

	/**
	 * Gets the value of the store property.
	 * 
	 * @return possible object is {@link Store }
	 * 
	 */
	public Store getStore() {
		return store;
	}

	/**
	 * Sets the value of the store property.
	 * 
	 * @param value
	 *            allowed object is {@link Store }
	 * 
	 */
	public void setStore(Store value) {
		this.store = value;
	}

	public int getDispensedAsWritten() {
		return dispensedAsWritten;
	}

	public void setDispensedAsWritten(int dispensedAsWritten) {
		this.dispensedAsWritten = dispensedAsWritten;
	}

	public Drug getTransmittedDrug() {
		return transmittedDrug;
	}

	public void setTransmittedDrug(Drug transmittedDrug) {
		this.transmittedDrug = transmittedDrug;
	}

	public String getRefillQualifier() {
		return refillQualifier;
	}

	public void setRefillQualifier(String refillQualifier) {
		this.refillQualifier = refillQualifier;
	}

	public Sig getSig() {
		return sig;
	}

	public void setSig(Sig sig) {
		this.sig = sig;
	}


	public double getTransformedQuantityPrescribed() {
		return transformedQuantityPrescribed;
	}


	public void setTransformedQuantityPrescribed(
			double transformedQuantityPrescribed) {
		this.transformedQuantityPrescribed = transformedQuantityPrescribed;
	}
}
