package com.walgreens.pharmacy.rules;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductCategory")
/**
 * Defines the categories in which drugs belong to
 * Track the dates for computing the most recent added product to the category.
 * 
 *
 */
public class ProductCategory {

	@XmlElement
	protected String drugId;
	@XmlElement
	protected CategoryType category;
	@XmlElement
	protected Date updateDate;
	@XmlElement
	protected Date creationDate;
	
	public ProductCategory(){}

	public String getDrugId() {
		return drugId;
	}

	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}

	public CategoryType getCategory() {
		return category;
	}

	public void setCategory(CategoryType category) {
		this.category = category;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
}
