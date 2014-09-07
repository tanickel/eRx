package com.walgreens.pharmacy.rules;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Drug")
/**
 * Drug: 
 * Updates:
* 08/25/2014: JBoyer: add a method to compute if the NDS is a core 9 or not see @@C1
* 08/26/2014: JBoyer/ OChappel: rework the drug class and state rules
*/
public class Drug {
	// IC+ or medispan 
	@XmlElement
	protected String dataSource = "";
	// drug id is coming only from IC+
	@XmlElement
	protected String drugId = "";
	// this should specify if this is a brand or generic product
	@XmlElement
	protected DrugType type;
	@XmlElement(required = true)
	protected String name = "";
	@XmlElement(required = true)
	protected String nationalDrugCode = "";
	
	@XmlElement
	protected boolean transmittedQuantityIsMultipleOfPackageSize = false;
	@XmlElement
	protected boolean transmittedQuantityEqualsPackageSize = false;
	@XmlElement
	protected boolean onTherapeuticInclusionList = false;
	@XmlElement
	protected CalculatedValues calculatedValues;
	@XmlElement
	protected List<PreferredGenericProduct> preferredGenericProduct;
	@XmlElement
	protected SubstitutionIndicator substitutionIndicator;
	@XmlElement(required = true)
	protected StatusType status; // ICPLUS drug is active, discontinued, no
									// longer manufactured

	@XmlElement
	protected boolean compoundDrug;

	@XmlElement
	protected DrugClassType drugClass; 
	// represents dea drug class coming from IC+ 
	// TODO assess if needed
	@XmlElement
	protected DrugConstraint federalDrugConstraint;
	@XmlElement
	protected DrugConstraint stateDrugConstraint;
	@XmlElement
	protected Integer defaultDaysOfSupply = -1;
	@XmlElement
	protected String defaultSig = "";
	@XmlElement
	protected Boolean dispensingRestriction = false;
	@XmlElement(required = true)
	protected TranslatedDosageFormType translatedDosageForm;
	@XmlElement
	protected Integer dosesPerPackage;

	@XmlElement
	protected Integer expirationDays;
	@XmlElement
	protected boolean excluded;
	@XmlElement
	protected boolean overridden;
	@XmlElement
	protected String genericProductIdentifier = "";
	@XmlElement
	protected Boolean limitedDistributionCode = false;
	@XmlElement
	protected Boolean linked = false;
	@XmlElement
	protected Boolean maintenanceDrug = false;
	@XmlElement(required = true)
	protected double minimumDispensingQuantity;


	@XmlElement
	protected String orangeBookRating = "";
	@XmlElement(required = true)
	protected double packageSize;
	@XmlElement(required = true)
	protected int packageQuantity;

	@XmlElement(required = true)
	protected PackageType packageType;
	// the next two attributes will help to assess if it is liquid
	// AERO, AERB, AERS, BAR, BEAD, SUSPS....
	@XmlElement
	protected String dosageFormCode = "";
	// IN, EX, NA, OR, CO, TL
	@XmlElement
	protected String routeOfAdministration = "";
	@XmlElement
	protected String storePreferredDrugId = "";

	@XmlElement(required = true)
	protected int therapeuticClass;
	@XmlElement
	protected Boolean validQuantityQualifier = true;

	@XmlElement
	protected String quantityUOM = "";
	@XmlElement
	protected Boolean validTherapeuticClass = false;
	@XmlElement
	protected Double totalNumberOfPackages;
	@XmlElement
	// The list of category the drug belong too. A drug could be in warehouse and store preferred. 
	protected List<ProductCategory> categories = new ArrayList<ProductCategory>();
	// used when selecting a warehouse product, to identify that the product may  be selected multiple time 
	protected String multiHitIndicator; // Y or N
	protected double walgreenAcquisitionCost;
	
	public Drug(){}
	
	/**
	 * 
	 * @param national drug code
	 * @return true if the given ndc is the same as this drug.
	 */
	public boolean isSameNDCCore9(String ndc){
		if ( ndc == null 
				  || ndc.length() < 9 || getNationalDrugCode().length() < 9 ) return false;
				return getNationalDrugCode().substring(0,9).equals(ndc.substring(0,9));
	}


	public String toString(){
		StringBuffer sb = new StringBuffer("Drug  Stack Trace [");
		sb.append("   DID= ").append(getDrugId());
		sb.append(" NDC:"+getNationalDrugCode());
		sb.append(" GPI:"+getGenericProductIdentifier());
		sb.append(" name:"+getName());
		sb.append("   DrugType= ").append(getType());
		sb.append(",  PackageType= ").append(getPackageType());
		sb.append(",  PackageSize= ").append(getPackageSize());
		sb.append(",  TotalPackages= ").append(getTotalNumberOfPackages());
		sb.append(",  ").append(" ]");
		return sb.toString();
	}

	public StatusType getStatus() {
		return status;
	}

	public void setStatus(StatusType status) {
		this.status = status;
	}

	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}

	/**
	 * Gets the value of the derivedValues property.
	 * 
	 * @return possible object is {@link CalculatedValues }
	 * 
	 */
	public CalculatedValues getCalculatedValues() {
		// Need to protect the rule execution in case the input data does not have that
		//if (calculatedValues == null) calculatedValues= new CalculatedValues();
		return calculatedValues;
	}

	/**
	 * Sets the value of the derivedValues property.
	 * 
	 * @param value
	 *            allowed object is {@link CalculatedValues }
	 * 
	 */
	public void setCalculatedValues(CalculatedValues value) {
		this.calculatedValues = value;
	}

	public SubstitutionIndicator getSubstitutionIndicator() {
		return substitutionIndicator;
	}

	public void setSubstitutionIndicator(
			SubstitutionIndicator substitutionIndicator) {
		this.substitutionIndicator = substitutionIndicator;
	}

	/**
	 * Gets the value of the preferredGenericProduct property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the preferredGenericProduct property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getPreferredGenericProduct().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link PreferredGenericProduct }
	 * 
	 * 
	 */
	public List<PreferredGenericProduct> getPreferredGenericProduct() {
		if (preferredGenericProduct == null) {
			preferredGenericProduct = new ArrayList<PreferredGenericProduct>();
		}
		return this.preferredGenericProduct;
	}

	/**
	 * Sets the value of the compoundDrug property.
	 * 
	 * @param value
	 *            allowed object is {@link Boolean }
	 * 
	 */
	public void setCompoundDrug(boolean value) {
		this.compoundDrug = value;
	}

	public boolean isCompoundDrug() {
		return compoundDrug;
	}

	
	/**
	 * Sets the value of the defaultDaysSupply property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setDefaultDaysOfSupply(Integer value) {
		this.defaultDaysOfSupply = value;
	}

	/**
	 * Sets the value of the defaultSig property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDefaultSig(String value) {
		this.defaultSig = value;
	}

	/**
	 * Sets the value of the dispensingRestriction property.
	 * 
	 * @param value
	 *            allowed object is {@link Boolean }
	 * 
	 */
	public void setDispensingRestriction(Boolean value) {
		this.dispensingRestriction = value;
	}

	public Boolean getDispensingRestriction() {
		return dispensingRestriction;
	}

	/**
	 * Gets the value of the dosageForm property.
	 * 
	 * @return possible object is {@link TranslatedDosageFormType }
	 * 
	 */
	public TranslatedDosageFormType getTranslatedDosageForm() {
		return translatedDosageForm;
	}

	/**
	 * Sets the value of the dosageForm property.
	 * 
	 * @param value
	 *            allowed object is {@link TranslatedDosageFormType }
	 * 
	 */
	public void setTranslatedDosageForm(TranslatedDosageFormType value) {
		this.translatedDosageForm = value;
	}

	/**
	 * Sets the value of the dosesPerPackage property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setDosesPerPackage(Integer value) {
		this.dosesPerPackage = value;
	}

	/**
	 * Sets the value of the expirationDays property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setExpirationDays(Integer value) {
	
		this.expirationDays = value;
	}

	/**
	 * Sets the value of the excluded property.
	 * 
	 * @param value
	 *            allowed object is {@link Boolean }
	 * 
	 */
	public void setExcluded(boolean value) {
		this.excluded = value;
	}

	/**
	 * Sets the value of the genericProductIdentifier property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setGenericProductIdentifier(String value) {
		this.genericProductIdentifier = value;
	}

	/**
	 * Sets the value of the limitedDistribution property.
	 * 
	 * @param value
	 *            allowed object is {@link Boolean }
	 * 
	 */
	public void setLimitedDistributionCode(Boolean value) {
		this.limitedDistributionCode = value;
	}

	/**
	 * Sets the value of the linked property.
	 * 
	 * @param value
	 *            allowed object is {@link Boolean }
	 * 
	 */
	public void setLinked(Boolean value) {
		this.linked = value;
	}

	/**
	 * Sets the value of the maintenanceDrug property.
	 * 
	 * @param value
	 *            allowed object is {@link Boolean }
	 * 
	 */
	public void setMaintenanceDrug(Boolean value) {
		this.maintenanceDrug = value;
	}

	public Boolean getMaintenanceDrug() {
		return maintenanceDrug;
	}

	/**
	 * Gets the value of the minimumDispensingQuantity property.
	 * 
	 */
	public double getMinimumDispensingQuantity() {
		return minimumDispensingQuantity;
	}

	/**
	 * Sets the value of the minimumDispensingQuantity property.
	 * 
	 */
	public void setMinimumDispensingQuantity(double value) {
		this.minimumDispensingQuantity = value;
	}

	/**
	 * Gets the value of the name property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the value of the name property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setName(String value) {
		this.name = value;
	}

	/**
	 * Gets the value of the nationalDrugCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getNationalDrugCode() {
		return nationalDrugCode;
	}

	/**
	 * Sets the value of the nationalDrugCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setNationalDrugCode(String value) {
		this.nationalDrugCode = value;
	}

	/**
	 * Gets the value of the orangeBookRating property.
	 * 
	 * @return possible object is {@link OrangeBookRatingType }
	 * 
	 */
	public String getOrangeBookRating() {
		return this.orangeBookRating;
	}

	/**
	 * Sets the value of the orangeBookRating property.
	 * 
	 * @param value
	 *            allowed object is {@link OrangeBookRatingType }
	 * 
	 */
	public void setOrangeBookRating(String value) {
		this.orangeBookRating = value;
	}

	/**
	 * Gets the value of the packageSize property.
	 * 
	 */
	public double getPackageSize() {
		return packageSize;
	}

	/**
	 * Sets the value of the packageSize property.
	 * 
	 */
	public void setPackageSize(double value) {
		this.packageSize = value;
	}

	/**
	 * Gets the value of the packageQuantity property.
	 * 
	 */
	public int getPackageQuantity() {
		return packageQuantity;
	}

	/**
	 * Sets the value of the packageQuantity property.
	 * 
	 */
	public void setPackageQuantity(int value) {
		this.packageQuantity = value;
	}

	/**
	 * Gets the value of the packageType property.
	 * 
	 * @return possible object is {@link PackageType }
	 * 
	 */
	public PackageType getPackageType() {
		if (packageType == null)
			return PackageType.UNSPECIFIED;
		return packageType;
	}

	/**
	 * Sets the value of the packageType property.
	 * 
	 * @param value
	 *            allowed object is {@link PackageType }
	 * 
	 */
	public void setPackageType(PackageType value) {
		this.packageType = value;
	}

	/**
	 * Sets the value of the routeOfAdministration property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRouteOfAdministration(String value) {
		this.routeOfAdministration = value;
	}

	/**
	 * Sets the value of the storePreferredDrugIdentifier property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setStorePreferredDrugId(String value) {
		this.storePreferredDrugId = value;
	}

	
	/**
	 * Gets the value of the therapeuticClass property.
	 * 
	 */
	public int getTherapeuticClass() {
		return therapeuticClass;
	}

	/**
	 * Sets the value of the therapeuticClass property.
	 * 
	 */
	public void setTherapeuticClass(int value) {
		this.therapeuticClass = value;
	}

	/**
	 * Gets the value of the type property.
	 * 
	 * @return possible object is {@link DrugType }
	 * 
	 */
	public DrugType getType() {
		return type;
	}

	/**
	 * Sets the value of the type property.
	 * 
	 * @param value
	 *            allowed object is {@link DrugType }
	 * 
	 */
	public void setType(DrugType value) {
		this.type = value;
	}





	public void setPreferredGenericProduct(List<PreferredGenericProduct> arg) {
		preferredGenericProduct = arg;
	}

	public Integer getDefaultDaysOfSupply() {
		Integer ret = defaultDaysOfSupply;
		if (defaultDaysOfSupply == null) {
			ret = new Integer(-1);
		}
		return (ret);
	}

	public String getDefaultSig() {
		String ret = defaultSig;
		if (defaultSig == null) {
			ret = new String();
		}
		return (ret);
	}

	public Boolean isDispensingRestriction() {
		Boolean ret = dispensingRestriction;
		if (dispensingRestriction == null) {
			ret = new Boolean(false);
		}
		return (ret);
	}

	public Integer getDosesPerPackage() {
		Integer ret = dosesPerPackage;
		if (dosesPerPackage == null) {
			ret = new Integer(0);
		}
		return (ret);
	}

	public String getDrugId() {

		return this.drugId;
	}

	public Integer getExpirationDays() {
		Integer ret = expirationDays;
		if (expirationDays == null) {
			ret = new Integer(0);
		}
		return (ret);
	}

	public boolean isExcluded() {
		return excluded;
	}

	public String getGenericProductIdentifier() {
		String ret = genericProductIdentifier;
		if (genericProductIdentifier == null) {
			ret = new String();
		}
		return (ret);
	}

	public Boolean isLimitedDistributionCode() {
		Boolean ret = limitedDistributionCode;
		if (limitedDistributionCode == null) {
			ret = new Boolean(false);
		}
		return (ret);
	}

	public Boolean isLinked() {
		Boolean ret = linked;
		if (linked == null) {
			ret = new Boolean(false);
		}
		return (ret);
	}

	public Boolean isMaintenanceDrug() {
		Boolean ret = maintenanceDrug;
		if (maintenanceDrug == null) {
			ret = new Boolean(false);
		}
		return (ret);
	}

	public String getRouteOfAdministration() {
		String ret = routeOfAdministration;
		if (routeOfAdministration == null) {
			ret = new String();
		}
		return (ret);
	}

	public String getStorePreferredDrugId() {

		return this.storePreferredDrugId;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * Sets the value of the validQuantityQualifier property.
	 * 
	 * @param value
	 *            allowed object is {@link Boolean }
	 * 
	 */
	public void setValidQuantityQualifier(Boolean value) {
		this.validQuantityQualifier = value;
	}

	public Boolean getValidQuantityQualifier() {
		Boolean ret = validQuantityQualifier;
		if (validQuantityQualifier == null) {
			ret = new Boolean(false);
		}
		return (ret);
	}

	/**
	 * Sets the value of the quantityUOM property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setQuantityUOM(String value) {
		this.quantityUOM = value;
	}

	public String getQuantityUOM() {
		String ret = quantityUOM;
		if (quantityUOM == null) {
			ret = new String();
		}
		return (ret);
	}

	/**
	 * Sets the value of the dosageFormCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDosageFormCode(String value) {
		this.dosageFormCode = value;
	}

	public String getDosageFormCode() {
		String ret = dosageFormCode;
		if (dosageFormCode == null) {
			ret = new String();
		}
		return (ret);
	}

	/**
	 * Sets the value of the validTherapeutic property.
	 * 
	 * @param value
	 *            allowed object is {@link Boolean }
	 * 
	 */
	public void setValidTherapeuticClass(Boolean value) {
		this.validTherapeuticClass = value;
	}

	public Boolean isValidTherapeuticClass() {
		Boolean ret = validTherapeuticClass;
		if (validTherapeuticClass == null) {
			ret = new Boolean(false);
		}
		return (ret);
	}

	public Double getTotalNumberOfPackages() {
		Double ret = totalNumberOfPackages;
		if (totalNumberOfPackages == null) {
			ret = new Double(0);
		}
		return (ret);
	}

	public void setTotalNumberOfPackages(Double totalNumberOfPackages) {
		this.totalNumberOfPackages = totalNumberOfPackages;
	}


	public boolean isOverridden() {
		return overridden;
	}

	public void setOverridden(boolean overridden) {
		this.overridden = overridden;
	}


	public boolean isOnTherapeuticInclusionList() {
		return onTherapeuticInclusionList;
	}

	public void setOnTherapeuticInclusionList(boolean onTherapeuticInclusionList) {
		this.onTherapeuticInclusionList = onTherapeuticInclusionList;
	}

	public boolean isTransmittedQuantityIsMultipleOfPackageSize() {
		return transmittedQuantityIsMultipleOfPackageSize;
	}

	public void setTransmittedQuantityIsMultipleOfPackageSize(
			boolean transmittedQuantityIsMultipleOfPackageSize) {
		this.transmittedQuantityIsMultipleOfPackageSize = transmittedQuantityIsMultipleOfPackageSize;
	}

	public boolean isTransmittedQuantityEqualsPackageSize() {
		return transmittedQuantityEqualsPackageSize;
	}

	public void setTransmittedQuantityEqualsPackageSize(
			boolean transmittedQuantityEqualsPackageSize) {
		this.transmittedQuantityEqualsPackageSize = transmittedQuantityEqualsPackageSize;
	}

	public DrugConstraint getFederalDrugConstraint() {
		return federalDrugConstraint;
	}

	public void setFederalDrugConstraint(DrugConstraint federalDrugConstraint) {
		this.federalDrugConstraint = federalDrugConstraint;
	}

	public DrugConstraint getStateDrugConstraint() {
		return stateDrugConstraint;
	}

	public void setStateDrugConstraint(DrugConstraint stateDrugConstraint) {
		this.stateDrugConstraint = stateDrugConstraint;
	}

	public void setDrugClass(DrugClassType drugClass) {
		this.drugClass = drugClass;
	}

	public void addProductCategory(ProductCategory pc) {
		getCategories().add(pc);
		
	}

	public List<ProductCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<ProductCategory> categories) {
		this.categories = categories;
	}

	public boolean isInWarehouse(){
		for (ProductCategory c : getCategories()) {
			if (CategoryType.Warehouse.equals(c.getCategory())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isNotInWarehouse(){
		return !(isInWarehouse());
	}
	
	public boolean isStorePreferred(){
		for (ProductCategory c : getCategories()) {
			if (CategoryType.StorePreferred.equals(c.getCategory())) {
				return true;
			}
		}
		return false;
	}
	
	public DrugClassType getDrugClass() {
		return drugClass;
	}

	public String getMultiHitIndicator() {
		return multiHitIndicator;
	}

	public void setMultiHitIndicator(String multiHitIndicator) {
		this.multiHitIndicator = multiHitIndicator;
	}

	public double getWalgreenAcquisitionCost() {
		return walgreenAcquisitionCost;
	}

	public void setWalgreenAcquisitionCost(double walgreenAquisitionCost) {
		this.walgreenAcquisitionCost = walgreenAquisitionCost;
	}

}
