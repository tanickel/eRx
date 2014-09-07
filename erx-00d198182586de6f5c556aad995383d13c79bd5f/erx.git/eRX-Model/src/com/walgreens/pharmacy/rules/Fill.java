package com.walgreens.pharmacy.rules;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Fill", propOrder = { "billToPlan", "cobPlan", "paymentMethod",
		"unlimited", "payCode" })
public class Fill {
	@XmlElement
	protected Plan billToPlan;
	@XmlElement
	protected Plan cobPlan;
	@XmlElement(required = true)
	protected PaymentMethodType paymentMethod;
	@XmlElement
	protected Boolean unlimited = false;
	@XmlElement(required = true)
	protected String payCode;

	public Fill(){}
	
	/**
	 * Gets the value of the billToPlan property.
	 * 
	 * @return possible object is {@link PatientPlan }
	 * 
	 */
	public Plan getBillToPlan() {
		return billToPlan;
	}

	/**
	 * Sets the value of the billToPlan property.
	 * 
	 * @param value
	 *            allowed object is {@link PatientPlan }
	 * 
	 */
	public void setBillToPlan(Plan value) {
		this.billToPlan = value;
	}

	/**
	 * Gets the value of the cobPlan property.
	 * 
	 * @return possible object is {@link PatientPlan }
	 * 
	 */
	public Plan getCobPlan() {
		return cobPlan;
	}

	/**
	 * Sets the value of the cobPlan property.
	 * 
	 * @param value
	 *            allowed object is {@link PatientPlan }
	 * 
	 */
	public void setCobPlan(Plan value) {
		this.cobPlan = value;
	}

	/**
	 * Gets the value of the paymentMethod property.
	 * 
	 * @return possible object is {@link PaymentMethodType }
	 * 
	 */
	public PaymentMethodType getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * Sets the value of the paymentMethod property.
	 * 
	 * @param value
	 *            allowed object is {@link PaymentMethodType }
	 * 
	 */
	public void setPaymentMethod(PaymentMethodType value) {
		this.paymentMethod = value;
	}

	/**
	 * Sets the value of the unlimited property.
	 * 
	 * @param value
	 *            allowed object is {@link Boolean }
	 * 
	 */
	public void setUnlimited(Boolean value) {
		this.unlimited = value;
	}

	/**
	 * Gets the value of the payCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPayCode() {
		return payCode;
	}

	/**
	 * Sets the value of the payCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPayCode(String value) {
		this.payCode = value;
	}

	/**
	 * public Boolean isUnlimited() { Boolean ret = unlimited; if (unlimited ==
	 * null ) { ret = new Boolean(false); } return (ret); }
	 **/
	public Boolean getUnlimited() {
		return unlimited;
	}

}
