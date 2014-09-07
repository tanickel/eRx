package com.walgreens.pharmacy.rules.ut.factory;

import java.util.ArrayList;
import java.util.List;

import com.walgreens.pharmacy.rules.CategoryType;
import com.walgreens.pharmacy.rules.Drug;
import com.walgreens.pharmacy.rules.DrugClassType;
import com.walgreens.pharmacy.rules.DrugConstraint;
import com.walgreens.pharmacy.rules.DrugType;
import com.walgreens.pharmacy.rules.ErxTransaction;
import com.walgreens.pharmacy.rules.PackageType;
import com.walgreens.pharmacy.rules.PreferredGenericProduct;
import com.walgreens.pharmacy.rules.Prescription;
import com.walgreens.pharmacy.rules.ProductCategory;
import com.walgreens.pharmacy.rules.StateType;
import com.walgreens.pharmacy.rules.StatusType;
import com.walgreens.pharmacy.rules.Store;
import com.walgreens.pharmacy.ut.DateUtilities;

/**
 * The goal of this class is to create the elements of the transaction, prescription, substitution drugs
 * to execute successfully the rules.
 * 
 * @author boyerje
 *
 */
public class DrugFactory {
	
	protected int ndcCount = 1;
	protected int drugIdCount = 1;

	/**
	 * A basic drug to unit test. Each UT will override value to trigger rule conditions
	 * The drug is a brand, tablet, controlling, unit of use
	 * @return
	 */
	public static Drug buildActiveTabletDrug(){
		Drug d=new Drug();
		d.setDataSource("IC+");
		d.setPackageSize(30);
		d.setPackageType(PackageType.UNIT_OF_USE);
		d.setDrugId("646070");
		d.setNationalDrugCode("00009004516");
		d.setDosageFormCode("CHEW");
		d.setRouteOfAdministration("OR");
		d.setGenericProductIdentifier("5816007000305");
		d.setType(DrugType.BRAND);
		d.setStatus(StatusType.ACTIVE);
		d.setOrangeBookRating("A");
		
		DrugConstraint sr = new DrugConstraint();
		sr.setDrugClass(DrugClassType.RX);
		sr.setMaxDaysToFill(20);
		sr.setTotalFillsAllowed("200");
		d.setStateDrugConstraint(sr);
		return d;
	}
	
	public static Drug buildControllingActiveTabletDrug(){
		Drug d= buildActiveTabletDrug();
		ProductCategory pc = new ProductCategory();
		pc.setCategory(CategoryType.Controlling);
		pc.setDrugId(d.getDrugId());
		pc.setCreationDate(DateUtilities.makeDate(2014, 01,03));
		pc.setUpdateDate(DateUtilities.makeDate(2014, 04,30));
		d.addProductCategory(pc);
		return d;
	}
	
	public static  Drug buildPreferredTabletGenericDrug(){
		Drug d = buildActiveTabletDrug();
		d.setType(DrugType.GENERIC);
		ProductCategory pc = new ProductCategory();
		pc.setCategory(CategoryType.StorePreferred);
		pc.setDrugId(d.getDrugId());
		pc.setCreationDate(DateUtilities.makeDate(2014, 01,03));
		pc.setUpdateDate(DateUtilities.makeDate(2014, 04,30));
		d.addProductCategory(pc);
		return d;
	}
	
	/**
	 * Define on transmitted drug 
	 * @return
	 */
	public static Prescription buildTabletDrugPrecription(){
		Prescription p = new Prescription();
		p.setOriginalDate(DateUtilities.makeDate(2014,8,11));
		// should match one of the drug in substitution drug list
        p.setTransmittedNdc("00009004516");
        p.setQuantityPrescribed(30);
        // orange book state
        Store store= new Store();
        store.setInOrangeBookState(true);
        store.setState(StateType.CA);
        p.setStore(store);
	
		return p;
	}
	
	public static ErxTransaction buildERXtxTabletDrugPrecription(){
		ErxTransaction t = new ErxTransaction();
		t.setTransactionDateTime(DateUtilities.makeDate(2014,8,11));
		// At each call the Transaction is bringing the list of substitution drugs that
		// could apply to this prescription
		ArrayList<Drug> substitutionDrugList= new ArrayList<Drug>();
		// add only one drug that match the transmitted drug
		substitutionDrugList.add(buildControllingActiveTabletDrug());
		t.setSubstitutionDrugList(substitutionDrugList);
		Prescription p = buildTabletDrugPrecription();
		t.setPrescription(p);
		return t;
	}
	
	public static void addPreferredGProd(Drug d){
	    List<PreferredGenericProduct> pgp = new ArrayList<PreferredGenericProduct>();
	    PreferredGenericProduct p1 = new PreferredGenericProduct();
	    p1.setDrugId(d.getDrugId());
	    pgp.add(p1);
		d.setPreferredGenericProduct(pgp);
	}
	
	protected String incrementNDC(String str){
		int i = Integer.parseInt(str);
		StringBuilder sb = new StringBuilder();
		sb.append(i +ndcCount);
		return sb.toString();
	}
	
	protected String incrementDrugId(String str){
		int i = Integer.parseInt(str);
		StringBuilder sb = new StringBuilder();
		sb.append(i +drugIdCount);
		return sb.toString();
	}
}
