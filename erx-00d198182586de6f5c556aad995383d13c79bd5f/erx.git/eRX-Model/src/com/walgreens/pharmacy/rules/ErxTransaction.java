package com.walgreens.pharmacy.rules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.walgreens.pharmacy.rules.util.DateTimeXmlAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ErxTransaction")
/**
 * This is the top root object for the rule processing. it is enriched by previous processing
 * before reaching the rules, so for example it includes the list of substitution drugs
 * than could apply to the drug specified in the prescription.
 * It is also referencing the patient.
 *
 *
 * Updates:
 * 08/20/2014: JBoyer: add a method to assign the transmitted drug see @@C1
 * 08/21/2014 JBoyer: Transform the model to add derived facts computed from the rules
 * but not needed for the consumers of the decision service. The change is tagged @@C2
 * store preferred drugs list contains the list of drug preferred by the pharmacy
 * controlling drug list is for WG preference
 * 08/25/2014 JBoyer: Add a candidate drug list to avoid modifying the substitution drug list so we can
 * have a better clarity on what the rules are computing as candidate drugs. The candidate drugs
 * will be used to build controlling, store preferred, warehouse drug list.  See @@C3
 * 08/25/2014: TNickel: add retainLargestPackageSizeLimitTo ; retainSmallestPackageSizeLimitTo    See @@C4
 * 08/27/2014: JBoyer: add retain lowest acquisition cost from a collection drugs and the retain @@C5
 */
public class ErxTransaction {

	@XmlElement(required = true)
	protected Prescription prescription;

	// input   ------------------------------------------------------ 
	@XmlElement
	protected ArrayList<Drug> substitutionDrugList = new ArrayList<Drug>();

	@XmlElement
	protected Patient patient;
	@XmlElement(required = true)
	@XmlJavaTypeAdapter(DateTimeXmlAdapter.class)
	@XmlSchemaType(name = "dateTime")
	protected Date transactionDateTime;
	@XmlElement
	protected ResultType completionStatus;
	@XmlElement
	protected String genericFor = "";
	@XmlElement
	protected boolean enableOrangeBookCheck = false;
	
	@XmlElement
	protected boolean enableAutomation = true; // Note: just for demo for phase
												// I and II. Phase three will
												// send in this attribute.
	// output/ derived attributes  ------------------------------------------------------ 
	// this will be the final substituted drug selected by the rules
	@XmlElement
	protected Drug substitutedDrug;
	// the following will be used if we need to add comments to the results
	@XmlElement
	protected List<String> annotations = new ArrayList<String>(); // added
																	// 08-07-2014

	// --- @@C2 Begin ---
	@XmlTransient
	protected Map<String,Drug> storePreferredDrugs = new HashMap<String,Drug>();
	@XmlTransient
	protected Map<String,Drug> controllingDrugs = new HashMap<String,Drug>();
	@XmlTransient
	protected Map<String,Drug> warehouseDrugs = new HashMap<String,Drug>();
	// @@C3 Begin ----
	@XmlTransient
	protected ArrayList<Drug> candidateDrugs = new ArrayList<Drug>();
	
	public ErxTransaction(){}
	
	public ArrayList<Drug> getCandidateDrugs() {
		
		return candidateDrugs;
	}

	public void setCandidateDrugs(ArrayList<Drug> candidateDrugs) {
		this.candidateDrugs = candidateDrugs;
	}
	public void addCandidateDrug(Drug d) {
		getCandidateDrugs().add(d);
	}
	public void removeCandidateDrug(Drug d) {
		getCandidateDrugs().remove(d);
	}
	public void setManualWhenCandidateDrugsIsEmpty(){
		if (!this.getCompletionStatus().equals(ResultType.MANUAL) && (this.getCandidateDrugs().size() == 0)) {
			this.setCompletionStatus(ResultType.MANUAL);
		}
	}
	// @@C3 End ----
	
	
	public Collection<Drug> getStorePreferredDrugs() {
		return storePreferredDrugs.values();
	}
	
	public void setStorePreferredDrugs(Collection<Drug> storePreferredDrugs) {
		for (Drug d : storePreferredDrugs)
		  addStorePreferredDrug(d);
	}
	
	public void addStorePreferredDrug(Drug d) {
		this.storePreferredDrugs.put(d.getDrugId(),d);
	}

	public Drug getStorePreferredDrugById(String did){
		return storePreferredDrugs.get(did);
	}
	
	public Collection<Drug> getControllingDrugs() {
		return controllingDrugs.values();
	}
	
	public void addControllingDrug(Drug d) {
		this.controllingDrugs.put(d.getDrugId(),d);
	}
	
	public Drug getControllingDrugById(String did){
		return controllingDrugs.get(did);
	}
	
	public void setControllingDrugs(Collection<Drug> controllingDrugs) {
		for (Drug d : controllingDrugs)
			addControllingDrug(d);
	}

	public Collection<Drug> getWarehouseDrugs() {
		return warehouseDrugs.values();
	}

	public Drug getWarehouseDrugById(String did){
		return warehouseDrugs.get(did);
	}
	public void addWarehouseDrug(Drug d) {
		this.warehouseDrugs.put(d.getDrugId(),d);
	}
	
	public void setWarehouseDrugs(Collection<Drug> warehouseDrugs) {
		for (Drug d : warehouseDrugs)
			addWarehouseDrug(d);
	}
	// --- @@ C2 End ---
	
	/**
	 * This function is added to avoid doing a subflow at the beginning of the rule flow. The goal is to
	 * assign the transmitted drug to one of the active drug that match the given National Drug code.
	 * If no active drug with same NDC is found, try inactive one, if non is found there is a problem in data
	 * so lets go manual.
	 * Attention: in the future may be this logic has to go back to rule if we think it need to be adapted overtime
	 * @@C1
	 */
	public boolean populateTransmittedDrug(){
		// verify there is one active drug
		boolean found = false;
		for(Drug d: getSubstitutionDrugList()) {
			if (d.getNationalDrugCode().equals(getPrescription().getTransmittedNdc()) 
					&& d.getStatus() == StatusType.ACTIVE) {
				getPrescription().setTransmittedDrug(d);
				found=true;
				break;
			}
		}
		if (! found) {
			// 
			for(Drug d: getSubstitutionDrugList()) {
				if (d.getNationalDrugCode().equals(getPrescription().getTransmittedNdc()) 
						&& d.getStatus() == StatusType.INACTIVE) {
					getPrescription().setTransmittedDrug(d);
					found=true;
					break;
				}
			}
		}
		if (! found) {
			setCompletionStatus(ResultType.MANUAL);
		}
		return found;
	}
	
	// @@C4 Begin ----
	
	public static Collection<Drug> retainLargestPackageSizeLessThan(Collection<Drug> c, int maxLimit){
		Collection<Drug> aList = new ArrayList<Drug>();
		if(c != null){
			for(Drug d: c){
				System.out.println("retainLargestPackageSizeLessThan::Pkg Size " + d.getTotalNumberOfPackages());
				if(d.getTotalNumberOfPackages() < maxLimit){
					aList.add(d);
				}
			}
		}
		if(!aList.isEmpty()){
			return retainLargestPackageSize(aList);
		}
		return c;
	}
	public static Collection<Drug> retainLargestPackageSize(Collection<Drug> c){
		double maxKey = -1;
		Collection<Drug> aCol = new ArrayList<Drug>();
		if(c != null){
			for(Drug d: c){
				System.out.println("retainLargestPackageSize::Pkg Size " + d.getTotalNumberOfPackages());
				if(d.getTotalNumberOfPackages() > maxKey){
					maxKey = d.getTotalNumberOfPackages();
				}
			}
			// add to the collection all drugs having the minimum cost
			if (maxKey>0) {
				for (Drug d: c) {
					if (d.getTotalNumberOfPackages() == maxKey) {
						aCol.add(d);
					}
				}
			}
		}
		return aCol;
	}
	

	public static Collection<Drug> retainSmallestPackageSizeLessThan(Collection<Drug> c, int maxLimit){
		Collection<Drug> aList = new ArrayList<Drug>();
		if(c != null){
			for(Drug d: c){
				System.out.println("retainSmallestPackageSizeLessThan::Pkg Size " + d.getTotalNumberOfPackages());
				if((d.getTotalNumberOfPackages() <=  maxLimit)){
					aList.add(d);
				}
			}
		}
		if(!aList.isEmpty()){
			System.out.println("Collection returned " + c.toString());
			return retainSmallestPackageSize(aList);
		}
		return c;
	}
	
	public static Collection<Drug> retainSmallestPackageSize(Collection<Drug> c){
		double minKey = 10;
		Collection<Drug> aCol = new ArrayList<Drug>();
		if(c != null){
			for(Drug d: c){
				System.out.println("retainSmallestPackageSize::Pkg Size " + d.getTotalNumberOfPackages());
				if(d.getTotalNumberOfPackages() < minKey){
					minKey = d.getTotalNumberOfPackages();
				
				}
			}
			// add to the collection all drugs having the minimum cost
			if (minKey>0) {
				for (Drug d: c) {
					if (d.getTotalNumberOfPackages() == minKey) {
						aCol.add(d);
					}
				}
			}
		}
		return aCol;
		
	}
	// @@C4 End ----
	// @@C5 Begin ---
	public static Collection<Drug> retainLowestAcquitionCost(Collection<Drug> c) {
		double minAcquisition =0;
		Collection<Drug> returnedDrugs = new ArrayList<Drug>();
		if(c != null){
			for (Drug d: c) {
				if (d.getWalgreenAcquisitionCost() < minAcquisition) {
					minAcquisition=d.getWalgreenAcquisitionCost() ;
				}
			}
			// add to the collection all drugs having the minimum cost
			if (minAcquisition>0) {
				for (Drug d: c) {
					if (d.getWalgreenAcquisitionCost() == minAcquisition) {
						returnedDrugs.add(d);
					}
				}
			}
		}
		return returnedDrugs;
	}
	/**
	 * Compute the newest drug from the given drug collection taking the time stamp as defined by its category 
	 * @param drug collection. The category of the drugs should be the same
	 * @return the drug
	 */
	public static Drug retainNewestDrug(Collection<Drug> c) {
		Drug newestDrug=null;
		if(c != null){
             Date newestDate = new Date(); // let take today as the date
			for (Drug d: c) {
				// To get the time stamp
				for (ProductCategory pc : d.getCategories()) {
					if (pc.getUpdateDate().before(newestDate)) {
							newestDate= pc.getUpdateDate();
							newestDrug=d;
					} 
				}
			}	
		}
		return newestDrug;
	}
	
	public static Drug retainOldestDrug(Collection<Drug> c) {
		Drug oldestDrug=null;
		if(c != null){
             Date oldestDate = new Date(0); // let take the oldest date of java :1970..
			for (Drug d: c) {
				for (ProductCategory pc : d.getCategories()) {
					if (pc.getCreationDate().before(oldestDate)) {
						oldestDate= pc.getCreationDate();
						oldestDrug=d;
					} 							
				}
			}	
		}
		return oldestDrug;
	}
	// @@C5 End ---
	
	public void addAnnotation(String value) {
		this.annotations.add(value);
	}

	public List<String> getAnnotations() {
		return this.annotations;
	}

	public void setAnnotations(List<String> annotations) {
		this.annotations = annotations;
	}

	/**
	 * Gets the value of the prescription property.
	 * 
	 * @return possible object is {@link Prescription }
	 * 
	 */
	public Prescription getPrescription() {
		return this.prescription;
	}

	/**
	 * Sets the value of the prescription property.
	 * 
	 * @param value
	 *            allowed object is {@link Prescription }
	 * 
	 */
	public void setPrescription(Prescription value) {
		this.prescription = value;
	}

	/**
	 * Gets the value of the patient property.
	 * 
	 * @return possible object is {@link Patient }
	 * 
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * Sets the value of the patient property.
	 * 
	 * @param value
	 *            allowed object is {@link Patient }
	 * 
	 */
	public void setPatient(Patient value) {
		this.patient = value;
	}


	/**
	 * Gets the value of the transactionDateTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public Date getTransactionDateTime() {
		return transactionDateTime;
	}

	/**
	 * Sets the value of the transactionDateTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTransactionDateTime(Date value) {
		this.transactionDateTime = value;
	}

	/**
	 * Gets the value of the completionStatus property.
	 * 
	 * @return possible object is {@link ResultType }
	 * 
	 */
	public ResultType getCompletionStatus() {
		if (completionStatus == null) {
			return ResultType.IN_PROGRESS;
		} else {
			return completionStatus;
		}
	}

	/**
	 * Sets the value of the completionStatus property.
	 * 
	 * @param value
	 *            allowed object is {@link ResultType }
	 * 
	 */
	public void setCompletionStatus(ResultType value) {
		this.completionStatus = value;
	}

	public void setSubstitutionDrugList(ArrayList<Drug> substitutionDrugList) {
		this.substitutionDrugList = substitutionDrugList;
	}

	public ArrayList<Drug> getSubstitutionDrugList() {
		return substitutionDrugList;
	}

	public Drug getSubstitutedDrug() {
		return substitutedDrug;
	}

	public void setSubstitutedDrug(Drug substitutedDrug) {
		this.substitutedDrug = substitutedDrug;
	}

	public String getGenericFor() {
		return genericFor;
	}

	public void setGenericFor(String genericFor) {
		this.genericFor = genericFor;
	}

	public boolean isEnableOrangeBookCheck() {
		return enableOrangeBookCheck;
	}

	public void setEnableOrangeBookCheck(boolean enableOrangeBookCheck) {
		this.enableOrangeBookCheck = enableOrangeBookCheck;
	}

	public boolean isEnableAutomation() {
		return enableAutomation;
	}

	public void setEnableAutomation(boolean enableAutomation) {
		this.enableAutomation = enableAutomation;
	}
	///This has not been unit tested
	public boolean drugsHaveSamePackageType(){
		Drug aDrug = new Drug();
		for( Drug d: substitutionDrugList){
			PackageType p = d.getPackageType();
			if(p != null && aDrug.getPackageType() != null)	{
				if(p.equals(aDrug.getPackageType())){
					return true;
				}
			}
		}
		return false;
	}
}
