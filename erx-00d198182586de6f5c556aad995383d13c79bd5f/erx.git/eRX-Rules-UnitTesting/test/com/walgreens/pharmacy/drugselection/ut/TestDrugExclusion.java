package com.walgreens.pharmacy.drugselection.ut;

import org.junit.Assert;
import org.junit.Test;

import com.walgreens.pharmacy.rules.DrugClassType;
import com.walgreens.pharmacy.rules.DrugConstraint;
import com.walgreens.pharmacy.rules.ErxTransaction;
import com.walgreens.pharmacy.rules.PackageType;
import com.walgreens.pharmacy.rules.ResultType;
import com.walgreens.pharmacy.rules.TranslatedDosageFormType;
import com.walgreens.pharmacy.rules.ut.factory.DrugFactory;
import com.walgreens.pharmacy.ut.BaseTest;

/**
 * Validate the following requirement 3.1.3
 * The system will determine if the transmitted NDC is on the Drug Exclusion list and will exclude the product from automation.
• NDC
• GPI (Full GPI – GPI14)
• Therapeutic  Class
• DEA class (based on local store state)
• Dosage Form
• Unit Dose package (unit of use)

The system will determine if the transmitted NDC is on the Drug Override list, which will override the products on the Drug Exclusion lists.

The Override List includes the following:
• NDC
• GPI (Full GPI – GPI14)
• Therapeutic  Class

 *
 */
public class TestDrugExclusion extends BaseTest {

	
	@Test
	/**
	 * if the transmitted drug with an NDC that is on the exclusion list it should be manual
	 * and set to excluded drug
	 * Following rules are executed
	 *  -Assess Manual Process Needs.Filter Exclusion and Overrides.NDC Drug Exclusions 1
	 *  - Assess Manual Process Needs.Filter Exclusion and Overrides.Dosage Drug Exclusions 1
	 */
	public void testDrugNDCExcludedByDT(){
		System.out.println("testDrugNDCExcludedByDT");
	    ErxTransaction tx= DrugFactory.buildERXtxTabletDrugPrecription();
		tx.getPrescription().setTransmittedNdc("12345");
	    tx.getSubstitutionDrugList().get(0).setNationalDrugCode("12345");
		ErxTransaction txo=rp.processDrugSelection(tx);
		printRulesFired();
		Assert.assertEquals(ResultType.MANUAL,txo.getCompletionStatus());
		Assert.assertTrue(txo.getPrescription().getTransmittedDrug().isExcluded());
	}
	
	@Test
	/**
	 * if the transmitted drug with an GPI that is on the exclusion list it should be manual
	 * and set to excluded drug
	 * 
	 * Assess Manual Process Needs.Filter Exclusion and Overrides.Dosage Drug Exclusions 1	
     * Assess Manual Process Needs.Filter Exclusion and Overrides.GPI Drug Exclusions 1
	 */
	public void testDrugGPIExcludedByDT(){
		System.out.println("testDrugGPIExcludedByDT");
	    ErxTransaction tx= DrugFactory.buildERXtxTabletDrugPrecription();
	    // as the rule will assign the transmitted drug from the substitution list we need
	    // to modify the substitution list drug to trigger the other rules
	    tx.getSubstitutionDrugList().get(0).setGenericProductIdentifier("6789");
		ErxTransaction txo=rp.processDrugSelection(tx);
		printRulesFired();
		Assert.assertEquals(ResultType.MANUAL,txo.getCompletionStatus());
		Assert.assertTrue(txo.getPrescription().getTransmittedDrug().isExcluded());
	}
	
	@Test
	/**
	 * Federal constraint for C2 product class
	 * 
	 * DEA Drug Exclusions 1
	 */
	public void testDEAdrugSelection(){
		System.out.println("testDEAdrugSelection C_2");
		ErxTransaction tx= DrugFactory.buildERXtxTabletDrugPrecription();
		DrugConstraint aConstraint= new DrugConstraint();
		aConstraint.setDrugClass(DrugClassType.C2);
		tx.getSubstitutionDrugList().get(0).setFederalDrugConstraint(aConstraint);
		tx.getSubstitutionDrugList().get(0).setDrugClass(DrugClassType.C2);
		
		ErxTransaction txo=rp.processDrugSelection(tx);
		Assert.assertEquals(ResultType.MANUAL,txo.getCompletionStatus());
		Assert.assertTrue(txo.getPrescription().getTransmittedDrug().isExcluded());
	}
	
	/**
	 * Federal constraint does not apply on a drug class type unspecified
	 * 
	 * DEA Drug Exclusions 1
	 */
	public void testDEAdrugOkToSelect(){
		System.out.println("testDEAdrugOkToSelect UNSPECIFIED");
		ErxTransaction tx= DrugFactory.buildERXtxTabletDrugPrecription();
		DrugConstraint aConstraint= new DrugConstraint();
		aConstraint.setDrugClass(DrugClassType.UNSPECIFIED);
		tx.getSubstitutionDrugList().get(0).setFederalDrugConstraint(aConstraint);
		tx.getSubstitutionDrugList().get(0).setDrugClass(DrugClassType.UNSPECIFIED);
		
		ErxTransaction txo=rp.processDrugSelection(tx);
		Assert.assertEquals(ResultType.IN_PROGRESS,txo.getCompletionStatus());
		Assert.assertFalse(txo.getPrescription().getTransmittedDrug().isExcluded());
	}
	
	@Test
	/**
	 * if the transmitted drug with an GPI that is on the exclusion list BUT the NDS is overridden
	 * Then it should be in progress and the drug not excluded
	 * As the NDC is overridden the process goes on automatic. 
	 * GPI Drug Exclusions 2
	 */
	public void testDrugGPIExcludedButNDCoverridden(){
		System.out.println("testDrugGPIExcludedButNDCoverridden");
		ErxTransaction tx= DrugFactory.buildERXtxTabletDrugPrecription();
	    tx.getSubstitutionDrugList().get(0).setGenericProductIdentifier("6789");
	    tx.getSubstitutionDrugList().get(0).setNationalDrugCode("819456789");
		tx.getPrescription().setTransmittedNdc("819456789");
		ErxTransaction txo=rp.processDrugSelection(tx);
		printRulesFired();
		Assert.assertEquals(ResultType.IN_PROGRESS,txo.getCompletionStatus());
		Assert.assertFalse(txo.getPrescription().getTransmittedDrug().isExcluded());
		Assert.assertTrue(txo.getPrescription().getTransmittedDrug().isOverridden());
	}

	@Test
	/**
	 * dosage is exclude
	 * 
	 * Dosage Drug Exclusions 1
	 */
	public void testTopicaldrugSelection(){
		System.out.println("testTopicaldrugSelection");
		ErxTransaction tx= DrugFactory.buildERXtxTabletDrugPrecription();
		tx.getSubstitutionDrugList().get(0).setDosageFormCode("AEPB");
		ErxTransaction txo=rp.processDrugSelection(tx);
		Assert.assertEquals(ResultType.MANUAL,txo.getCompletionStatus());
		Assert.assertTrue(txo.getPrescription().getTransmittedDrug().isExcluded());
	}
	
	@Test
	/**
	 * Unit dose is excluded
	 * Package type Drug Exclusions 1
	 */
	public void testUnitDosedrugSelection(){
		System.out.println("testUnitDosedrugSelection");
		ErxTransaction tx= DrugFactory.buildERXtxTabletDrugPrecription();
		tx.getSubstitutionDrugList().get(0).setPackageType(PackageType.UNIT_DOSE);
		ErxTransaction txo=rp.processDrugSelection(tx);
		Assert.assertEquals(ResultType.MANUAL,txo.getCompletionStatus());
		Assert.assertTrue(txo.getPrescription().getTransmittedDrug().isExcluded());
	}
	
	@Test
	/**
	 * Therapeutic class is excluded
	 * 
	 * Therapeutic Class Drug Exclusions 1
	 */
	public void testTherapeuticdrugSelection(){
		System.out.println("testTherapeuticdrugSelection");
		ErxTransaction tx= DrugFactory.buildERXtxTabletDrugPrecription();
		tx.getSubstitutionDrugList().get(0).setTherapeuticClass(1);
		ErxTransaction txo=rp.processDrugSelection(tx);
		Assert.assertEquals(ResultType.MANUAL,txo.getCompletionStatus());
		Assert.assertTrue(txo.getPrescription().getTransmittedDrug().isExcluded());
	}
}
