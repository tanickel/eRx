package com.walgreens.pharmacy.drugselection.ut;

import org.junit.Assert;
import org.junit.Test;

import com.walgreens.pharmacy.rules.Drug;
import com.walgreens.pharmacy.rules.DrugType;
import com.walgreens.pharmacy.rules.ErxTransaction;
import com.walgreens.pharmacy.rules.ResultType;
import com.walgreens.pharmacy.rules.StatusType;
import com.walgreens.pharmacy.rules.TranslatedDosageFormType;
import com.walgreens.pharmacy.rules.ut.factory.DrugFactory;
import com.walgreens.pharmacy.ut.BaseTest;

/**
 * These unit tests aim to validate all rules in preprocessing package 
 * @author boyerje
 *
 */
public class TestDrugSelectionPrepareCandidates extends BaseTest {

	@Test
	/**
	 * Validate that the initialized parameters at the beginning of the flow are set
	 */
	public void testEnableOrangeBook(){
		System.out.println("testEnableOrangeBook");
		ErxTransaction tx= DrugFactory.buildERXtxTabletDrugPrecription();
		ErxTransaction txo=rp.processDrugSelection(tx);
		printRulesFired();
		// the following assertion verify that the initialize orange book fired
		Assert.assertTrue(txo.isEnableOrangeBookCheck());
		// that the transmitted 
		Assert.assertEquals(TranslatedDosageFormType.TABLET,txo.getPrescription().getTransmittedDrug().getTranslatedDosageForm());
		// calculate total # of package
		Assert.assertEquals(ResultType.IN_PROGRESS,txo.getCompletionStatus());
	}
	
	@Test
	public void testComputedPackageNumber(){
		System.out.println("testComputedPackage Number");
		ErxTransaction tx= DrugFactory.buildERXtxTabletDrugPrecription();
		tx.getPrescription().setQuantityPrescribed(20);
	    tx.getSubstitutionDrugList().get(0).setPackageSize(5);
		ErxTransaction txo=rp.processDrugSelection(tx);
		printRulesFired();
		// calculate total # of package
		Assert.assertTrue(4 == txo.getSubstitutionDrugList().get(0).getTotalNumberOfPackages());	
		Assert.assertEquals(ResultType.IN_PROGRESS,txo.getCompletionStatus());
	}
	

	@Test
	/**
	 * As part of the substitution drugs there are drugs that have 'controlling' product as
	 * defined by corporate policy so the drugs are added to the controlling drug list.
	 * Add two controlling drugs, they should in the controlling list
	 * This test validates the Determine Controlling Products rule
	 */
	public void testControllingProduct(){
		System.out.println("testControllingProduct");
		ErxTransaction tx= DrugFactory.buildERXtxTabletDrugPrecription();
		
		Drug d2= DrugFactory.buildControllingActiveTabletDrug();
		d2.setDrugId("646071");
		d2.setNationalDrugCode("34");
		// be sure the controlling information applies to the same drug id
		d2.getCategories().get(0).setDrugId(d2.getDrugId());
		tx.getSubstitutionDrugList().add(d2);
		ErxTransaction txo=rp.processDrugSelection(tx);
		printRulesFired();
		Assert.assertEquals(2,txo.getControllingDrugs().size());
		Assert.assertFalse(ResultType.MANUAL == txo.getCompletionStatus());
	}
	
	@Test
	/**
	 * 
	 * As part of the substitution drugs there are drugs that have preferred by the store 
	 *  so the drugs are added to the store preferred drug list.
	 * 
	 * Determine Stored Preferred Products rule
	 * 
 	 */
	public void testStorePreferredProduct(){
		System.out.println("testStorePreferredProduct");
		ErxTransaction tx= DrugFactory.buildERXtxTabletDrugPrecription();
		// get the future transmitted drug and assign it a store preferred drug id
		Drug d1=tx.getSubstitutionDrugList().get(0);
		d1.setStorePreferredDrugId("650005");
		// add a drug that is store preferred
		Drug d2= DrugFactory.buildPreferredTabletGenericDrug();
		d2.setDrugId("650005");
		d2.setNationalDrugCode("34");
		d2.getCategories().get(0).setDrugId(d2.getDrugId());
		tx.getSubstitutionDrugList().add(d2);
		ErxTransaction txo=rp.processDrugSelection(tx);
		printRulesFired();
		Assert.assertTrue(txo.getStorePreferredDrugs().size()>0);
		// the swap is not done yet. ATTENTION this assertion may fail later
		Assert.assertEquals(StatusType.ACTIVE,txo.getPrescription().getTransmittedDrug().getStatus());
		Assert.assertEquals(ResultType.IN_PROGRESS,txo.getCompletionStatus());
	}
	

	@Test
	/**
	 * Remove drug with not the same GPI for generic drug
	 * rule 3.1.6
	 */
	public void removeSubstitutionDrugWithDifferentGPI() {
		System.out.println("removeSubstitutionDrugWithDifferentGPI");
		ErxTransaction tx= DrugFactory.buildERXtxTabletDrugPrecription();
		// GPI is 5816007000305
		// add a second drug with different GPI
		Drug d2= DrugFactory.buildActiveTabletDrug();
		d2.setGenericProductIdentifier("NotTheSame");
		d2.setNationalDrugCode("NDC different too");
		d2.setType(DrugType.BRAND);
		tx.getPrescription().setDispensedAsWritten(0);
		tx.getSubstitutionDrugList().add(d2);
		Assert.assertEquals(2,tx.getSubstitutionDrugList().size());
		ErxTransaction txo=rp.processDrugSelection(tx);
		printRulesFired();
		Assert.assertEquals(1,txo.getCandidateDrugs().size());
		Assert.assertEquals(ResultType.IN_PROGRESS,txo.getCompletionStatus());
	}
	
	@Test
	/**
	 * Verify the drugs that are active , BRAND and dispensed as written sets to N but do not
	 * have core 9 NDC equals to the Transmitted NDC's are not in the substitution list
	 */
	public void testDrugWithNotTheSameCore9NDC() {
		System.out.println("testDrugWithNotTheSameCore9NDC");
		ErxTransaction tx= DrugFactory.buildERXtxTabletDrugPrecription();
		// add a second drug with different GPI
		Drug d2= DrugFactory.buildActiveTabletDrug();
		d2.setNationalDrugCode("10009004516");
		d2.setType(DrugType.BRAND);
		tx.getPrescription().setDispensedAsWritten(0);
		tx.getSubstitutionDrugList().add(d2);
		Assert.assertEquals(2,tx.getSubstitutionDrugList().size());
		ErxTransaction txo=rp.processDrugSelection(tx);
		printRulesFired();
		Assert.assertEquals(1,txo.getCandidateDrugs().size());
		Assert.assertEquals(ResultType.IN_PROGRESS,txo.getCompletionStatus());
	}
	
	
}
