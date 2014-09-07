package com.walgreens.pharmacy.ft;

import java.util.ArrayList;
import java.util.List;

import com.walgreens.services.erx.dataentrybypassservice.ds.CategoryType;
import com.walgreens.services.erx.dataentrybypassservice.ds.Drug;
import com.walgreens.services.erx.dataentrybypassservice.ds.DrugClassType;
import com.walgreens.services.erx.dataentrybypassservice.ds.DrugConstraint;
import com.walgreens.services.erx.dataentrybypassservice.ds.DrugType;
import com.walgreens.services.erx.dataentrybypassservice.ds.ErxTransaction;
import com.walgreens.services.erx.dataentrybypassservice.ds.PackageType;
import com.walgreens.services.erx.dataentrybypassservice.ds.PreferredGenericProduct;
import com.walgreens.services.erx.dataentrybypassservice.ds.Prescription;
import com.walgreens.services.erx.dataentrybypassservice.ds.ProductCategory;
import com.walgreens.services.erx.dataentrybypassservice.ds.StateType;
import com.walgreens.services.erx.dataentrybypassservice.ds.StatusType;
import com.walgreens.services.erx.dataentrybypassservice.ds.Store;

/**
 * The goal of this class is to create the elements of the transaction, prescription, substitution drugs
 * to execute successfully the rules.
 * 
 * @author boyerje
 *
 */
public class DrugFactory {

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
		pc.setCategory(CategoryType.CONTROLLING);
		pc.setDrugId(d.getDrugId());
		pc.setCreationDate(DateUtilities.makeXmlDate(2014, 01,03,0,0));
		pc.setUpdateDate(DateUtilities.makeXmlDate(2014, 04,30,0,0));
		d.getCategories().add(pc);
		return d;
	}
	
	public static  Drug buildPreferredTabletGenericDrug(){
		Drug d = buildActiveTabletDrug();
		d.setType(DrugType.GENERIC);
		ProductCategory pc = new ProductCategory();
		pc.setCategory(CategoryType.STORE_PREFERRED);
		pc.setDrugId(d.getDrugId());
		pc.setCreationDate(DateUtilities.makeXmlDate(2014, 01,03,0,0));
		pc.setUpdateDate(DateUtilities.makeXmlDate(2014, 04,30,0,0));
		d.getCategories().add(pc);
		return d;
	}
	
	/**
	 * Define on transmitted drug 
	 * @return
	 */
	public static Prescription buildTabletDrugPrecription(){
		Prescription p = new Prescription();
		p.setOriginalDate(DateUtilities.makeXmlDate(2014,8,11,0,0));
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
		t.setTransactionDateTime(DateUtilities.makeXmlDate(2014,8,11,0,0));
		// At each call the Transaction is bringing the list of substitution drugs that
		// could apply to this prescription
		t.getSubstitutionDrugList().add(buildControllingActiveTabletDrug());
		Prescription p = buildTabletDrugPrecription();
		t.setPrescription(p);
		return t;
	}
	
	public static void addPreferredGProd(Drug d){
	    PreferredGenericProduct p1 = new PreferredGenericProduct();
	    p1.setDrugId(d.getDrugId());
		d.getPreferredGenericProduct().add(p1);
	}
}
