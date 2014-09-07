package com.walgreens.pharmacy.rules;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.walgreens.pharmacy.rules.util.DateTimeXmlAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Plan")
public class Plan {
	@XmlElement(required = true)
	@XmlJavaTypeAdapter(DateTimeXmlAdapter.class)
	@XmlSchemaType(name = "dateTime")
	protected Date creationDateTime;
	@XmlElement
	protected boolean excluded = false;
	@XmlElement(required = true)
	protected String id = ""; // was duplicate in patient plan class
	@XmlElement
	protected Boolean planOverrideFlag = false;
	@XmlElement(required = true)
	protected String subType = "";
	@XmlElement(required = true)
	protected PlanType type;
	@XmlElement(required = true)
	protected String pcn = "";
	@XmlAttribute(required = true)
	protected PaymentModeType paymentMode;
	@XmlAttribute
	protected String drugId = "";
	@XmlAttribute
	protected String genericProductIdentifier = "";
	@XmlAttribute(required = true)
	protected String groupNumber = "";

	public Plan(){}
	
	/**
	 * Gets the value of the creationDateTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public Date getCreationDateTime() {
		return creationDateTime;
	}

	/**
	 * Sets the value of the creationDateTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCreationDateTime(Date value) {
		this.creationDateTime = value;
	}




	/**
	 * Gets the value of the id property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the value of the id property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setId(String value) {
		this.id = value;
	}

	/**
	 * Gets the value of the inNewPlanExclusionList property.
	 * 
	 * @return possible object is {@link Boolean }
	 * 
	 */
	public Boolean isPlanOverrideFlag() {
		if (planOverrideFlag == null) {
			return false;
		} else {
			return planOverrideFlag;
		}
	}

	/**
	 * Sets the value of the inNewPlanExclusionList property.
	 * 
	 * @param value
	 *            allowed object is {@link Boolean }
	 * 
	 */
	public void setPlanOverrideFlag(Boolean value) {
		this.planOverrideFlag = value;
	}

	/**
	 * Gets the value of the subType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSubType() {
		return subType;
	}

	/**
	 * Sets the value of the subType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSubType(String value) {
		this.subType = value;
	}

	/**
	 * Gets the value of the type property.
	 * 
	 * @return possible object is {@link PlanType }
	 * 
	 */
	public PlanType getType() {
		return type;
	}

	/**
	 * Sets the value of the type property.
	 * 
	 * @param value
	 *            allowed object is {@link PlanType }
	 * 
	 */
	public void setType(PlanType value) {
		this.type = value;
	}

	/**
	 * Gets the value of the pcn property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPcn() {
		return pcn;
	}

	/**
	 * Sets the value of the pcn property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPcn(String value) {
		this.pcn = value;
	}

	public PaymentModeType getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(PaymentModeType paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getDrugId() {
		return drugId;
	}

	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}

	public String getGenericProductIdentifier() {
		return genericProductIdentifier;
	}

	public void setGenericProductIdentifier(String genericProductIdentifier) {
		this.genericProductIdentifier = genericProductIdentifier;
	}

	public boolean getExcluded() {
		return excluded;
	}

	public void setExcluded(boolean excluded) {
		this.excluded = excluded;
	}

	public String getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}

	public Boolean getPlanOverrideFlag() {
		return planOverrideFlag;
	}
}
