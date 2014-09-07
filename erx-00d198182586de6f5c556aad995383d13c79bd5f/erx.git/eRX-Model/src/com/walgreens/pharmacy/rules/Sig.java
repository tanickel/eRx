package com.walgreens.pharmacy.rules;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Sig")
public class Sig {
	@XmlElement
	protected List<Sig> listOfSubsigs = new ArrayList<Sig>();	
	@XmlElement(required = true)
    protected String directions = ""; //MAX, Maximum, MDD, Exceed, decrease, sprinkle, off;
	@XmlElement(required = true)
    protected int dose;
	@XmlElement(required = true)
    protected int duration;
	@XmlElement(required = true)
    protected int frequency;
	@XmlElement(required = true)
    protected int timePeriod;

	public Sig(){}
	
    /**
     * Gets the value of the directions property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirections() {
        return directions;
    }

    /**
     * Sets the value of the directions property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirections(String value) {
        this.directions = value;
    }

    /**
     * Gets the value of the dose property.
     * 
     */
    public int getDose() {
        return dose;
    }

    /**
     * Sets the value of the dose property.
     * 
     */
    public void setDose(int value) {
        this.dose = value;
    }

    /**
     * Gets the value of the duration property.
     * 
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     */
    public void setDuration(int value) {
        this.duration = value;
    }

    /**
     * Gets the value of the frequency property.
     * 
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * Sets the value of the frequency property.
     * 
     */
    public void setFrequency(int value) {
        this.frequency = value;
    }

    /**
     * Gets the value of the timePeriod property.
     * 
     */
    public int getTimePeriod() {
        return timePeriod;
    }

    /**
     * Sets the value of the timePeriod property.
     * 
     */
    public void setTimePeriod(int value) {
        this.timePeriod = value;
    }

	public List<Sig> getListOfSubsigs() {
		return listOfSubsigs;
	}

	public void setListOfSubsigs(List<Sig> listOfSubsigs) {
		this.listOfSubsigs = listOfSubsigs;
	}

	public void removeSig(Sig sig){
		if( sig != null){
			this.listOfSubsigs.remove(sig);
		}
	}

	public static boolean searchSubSigs(String directions,
			ArrayList<String> subSigs) {
		if (directions != null && subSigs.size()>0) {
			for (int i = 0; i < subSigs.size(); i++) {
				if (directions.trim().equalsIgnoreCase(subSigs.get(i))) {
					return true;

				}
			}
		}
		return false;
	}
}
