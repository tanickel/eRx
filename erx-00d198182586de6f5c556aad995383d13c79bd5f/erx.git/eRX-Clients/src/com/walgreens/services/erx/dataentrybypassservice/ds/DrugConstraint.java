//
// Generated By:JAX-WS RI IBM 2.2.1-11/25/2013 11:48 AM(foreman)- (JAXB RI IBM 2.2.3-11/25/2013 12:35 PM(foreman)-)
//


package com.walgreens.services.erx.dataentrybypassservice.ds;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DrugConstraint complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DrugConstraint">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="drugClass" type="{http://services.walgreens.com/erx/DataEntryByPassService/ds}DrugClassType"/>
 *         &lt;element name="maxDaysToFill" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="totalFillsAllowed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DrugConstraint", propOrder = {
    "drugClass",
    "maxDaysToFill",
    "totalFillsAllowed"
})
public class DrugConstraint {

    @XmlElement(required = true)
    protected DrugClassType drugClass;
    protected int maxDaysToFill;
    @XmlElement(required = true)
    protected String totalFillsAllowed;

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

    /**
     * Gets the value of the totalFillsAllowed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalFillsAllowed() {
        return totalFillsAllowed;
    }

    /**
     * Sets the value of the totalFillsAllowed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalFillsAllowed(String value) {
        this.totalFillsAllowed = value;
    }

}
