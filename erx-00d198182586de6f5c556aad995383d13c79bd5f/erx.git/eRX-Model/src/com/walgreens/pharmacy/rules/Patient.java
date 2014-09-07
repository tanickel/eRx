package com.walgreens.pharmacy.rules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.walgreens.pharmacy.rules.util.DateXmlAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Patient", propOrder = { "listOfPlans", "dateOfBirth" })
public class Patient {
	@XmlElement
	protected List<Plan> listOfPlans = new ArrayList<Plan>();
	@XmlElement
	@XmlJavaTypeAdapter(DateXmlAdapter.class)
	@XmlSchemaType(name = "date")
	protected Date dateOfBirth;

	public Patient(){}
	
	/**
	 * Sets the value of the dateOfBirth property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDateOfBirth(Date value) {
		this.dateOfBirth = value;
	}

	public Date getDateOfBirth() {
		Date ret = dateOfBirth;
		if (dateOfBirth == null) {
			ret = new Date(0);
		}
		return (ret);
	}

	public List<Plan> getListOfPlans() {
		return listOfPlans;
	}

	public void setListOfPlans(List<Plan> listOfPlans) {
		this.listOfPlans = listOfPlans;
	}
}
