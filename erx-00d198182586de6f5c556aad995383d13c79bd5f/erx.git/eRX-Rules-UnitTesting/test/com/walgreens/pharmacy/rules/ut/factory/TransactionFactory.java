package com.walgreens.pharmacy.rules.ut.factory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.walgreens.pharmacy.rules.CategoryType;
import com.walgreens.pharmacy.rules.Drug;
import com.walgreens.pharmacy.rules.DrugClassType;
import com.walgreens.pharmacy.rules.DrugConstraint;
import com.walgreens.pharmacy.rules.DrugType;
import com.walgreens.pharmacy.rules.ErxTransaction;
import com.walgreens.pharmacy.rules.PreferredGenericProduct;
import com.walgreens.pharmacy.rules.Prescription;
import com.walgreens.pharmacy.rules.ProductCategory;
import com.walgreens.pharmacy.rules.ResultType;
import com.walgreens.pharmacy.rules.StateType;
import com.walgreens.pharmacy.rules.StatusType;
import com.walgreens.pharmacy.rules.Store;
import com.walgreens.pharmacy.rules.TranslatedDosageFormType;
import com.walgreens.pharmacy.ut.DateUtilities;

public class TransactionFactory {
	
	
	/**
	 * A basic drug to unit test. Each UT will override value to trigger rule conditions
	 * @return
	 */
	public static Drug buildBasicDrug(){
		Drug d=new Drug();
		d.setDataSource("IC+");
		d.setPackageSize(30);
		d.setDrugId("646070");
		d.setNationalDrugCode("00009004516");
		d.setDosageFormCode("TABS");
		d.setGenericProductIdentifier("5816007000305");
		d.setTranslatedDosageForm(TranslatedDosageFormType.TABLET);
		d.setType(DrugType.UNSPECIFIED);
		
		DrugConstraint sr = new DrugConstraint();
		sr.setDrugClass(DrugClassType.RX);
		sr.setMaxDaysToFill(20);
		d.setStatus(StatusType.ACTIVE);
		sr.setTotalFillsAllowed("200");
		d.setStateDrugConstraint(sr);
		ProductCategory pc = new ProductCategory();
		pc.setCategory(CategoryType.Controlling);
		pc.setDrugId(d.getDrugId());
		d.addProductCategory(pc);
		return d;
	}
	
	/**
	 * Define on transmitted drug 
	 * @return
	 */
	public static Prescription buildBasicPrescription(){
		Prescription p = new Prescription();
		Store s = new Store();
		s.setState(StateType.IL);
		p.setStore(s);
		p.setOriginalDate(DateUtilities.makeDate(2014,8,11));
        p.setTransmittedNdc("00009004516");
        p.setOriginalDate(new Date(8-30-2014));
    
		return p;
	}
	
		
	public static ErxTransaction buildBasicERXtx(){
		ErxTransaction t = new ErxTransaction();
		t.setTransactionDateTime(DateUtilities.makeDate(2014,8,11));
		t.setCompletionStatus(ResultType.IN_PROGRESS);
		// At each call the Transaction is bringing the list of substitution drugs that
		// could apply to this prescription
		ArrayList<Drug> substitutionDrugList= new ArrayList<Drug>();
		// add only one drug
		substitutionDrugList.add(buildBasicDrug());  // Add at least 1 dummy drug to list
		t.setSubstitutionDrugList(substitutionDrugList);
		return t;
	}
	
	public static void addPreferredGProd(Drug d){
	    List<PreferredGenericProduct> pgp = new ArrayList<PreferredGenericProduct>();
	    PreferredGenericProduct p1 = new PreferredGenericProduct();
	    p1.setDrugId(d.getDrugId());
	    pgp.add(p1);
		d.setPreferredGenericProduct(pgp);
	}

}
