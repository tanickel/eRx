package com.walgreens.pharmacy.drugselection.ut;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.walgreens.pharmacy.rules.Drug;
import com.walgreens.pharmacy.rules.ErxTransaction;
import com.walgreens.pharmacy.rules.ResultType;
import com.walgreens.pharmacy.rules.StatusType;
import com.walgreens.pharmacy.rules.ut.factory.DrugFactory;
import com.walgreens.pharmacy.ut.BaseTest;
import com.walgreens.pharmacy.ut.DateUtilities;

/**
 * This set of tescases should validate each rule that makes the process goes manual as
 * early as possible. So validate all the rules as part of the first rule task.
 * 
 * Try to keep the order of test to match the order of the rule, it will be easier to see if we do not forget tests
 * @author boyerje
 *
 */
public class TestDrugSelectionGoManual extends BaseTest {
	

	@Test
	/**
	 * NDC is not found in the substitution list
	 */
	public void testTransmittedNDCnotFound(){
		System.out.println("testTransmittedNDCnotFound");
		ErxTransaction tx= DrugFactory.buildERXtxTabletDrugPrecription();
		tx.getPrescription().setTransmittedNdc("SomeThingWrong");
		saveTxToXMLFile(tx, "TransmittedNDCnotFound.xml");
		ErxTransaction txo=rp.processDrugSelection(tx);
		printRulesFired();
		Assert.assertEquals(ResultType.MANUAL,txo.getCompletionStatus());
	}
	
		
	@Test
	/**
	 * name:	Manual for 'not A' rated Orange Book drug.brl
	 */
	public void testDrugNotAInOrangeBook(){
		System.out.println("testDrugNotAInOrangeBook");
		ErxTransaction tx= DrugFactory.buildERXtxTabletDrugPrecription();
		// by default the state is CA so an orange book
		// set 
		tx.getSubstitutionDrugList().get(0).setOrangeBookRating("B");
		saveTxToXMLFile(tx, "DrugNotAInOrangeBook.xml");
		ErxTransaction txo=rp.processDrugSelection(tx);
		printRulesFired();
		Assert.assertEquals(ResultType.MANUAL,txo.getCompletionStatus());
		Assert.assertTrue(txo.isEnableOrangeBookCheck());
	}
	
	@Test
	/**
	 * a negative test is in fact validating that all the rules go well and that the factory is building 
	 * a good dataset.
	 */
	public void testDrugAInOrangeBook(){
		System.out.println("testDrugAInOrangeBook");
		ErxTransaction tx= DrugFactory.buildERXtxTabletDrugPrecription();
		saveTxToXMLFile(tx, "DrugAInOrangeBook.xml");
		ErxTransaction txo=rp.processDrugSelection(tx);
		printRulesFired();
		Assert.assertEquals(ResultType.IN_PROGRESS,txo.getCompletionStatus());
		Assert.assertTrue(txo.isEnableOrangeBookCheck());
	}
	



    @Test
	/**
	 * Test	Check for Multiple Active T_NDC in Drug List 
	 * in the drug substitute list there are drugs active and inactive with the same
	 * NDC as the transmitted drug
	 * 
	 * Check for Multiple Active T_NDC in Drug List
	 */
	public void testMultipleActiveNDC() throws IOException {
		System.out.println("testMultipleActiveNDC");
		ErxTransaction tx= DrugFactory.buildERXtxTabletDrugPrecription();
        // out of basic there is one active drug with same NDC
		// so let add one as inactive
		Drug d= DrugFactory.buildActiveTabletDrug();
		d.setStatus(StatusType.INACTIVE);
		tx.getSubstitutionDrugList().add(d);
		// add more than one WHICH is strange
		Drug d2= DrugFactory.buildActiveTabletDrug();
		d2.setStatus(StatusType.INACTIVE);
		tx.getSubstitutionDrugList().add(d2);
		Drug d3= DrugFactory.buildActiveTabletDrug();
		d3.setStatus(StatusType.ACTIVE);
		tx.getSubstitutionDrugList().add(d3);
		saveTxToXMLFile(tx, "MultipleActiveNDC.xml");
		ErxTransaction txo=rp.processDrugSelection(tx);
		printRulesFired();
		Assert.assertEquals(ResultType.MANUAL,txo.getCompletionStatus());
	}

	
	@Test
	/**
	 * No active drugs in substitution
	 * 
	 * Go Manual when no Active NDC
	 */
	public void testNoActiveDrugs(){
		System.out.println("testNoActiveDrugs");
		ErxTransaction tx= DrugFactory.buildERXtxTabletDrugPrecription();
	    // TODO THERE is a problem here. if there is not active drug, we are not copying any drug to transmitted drug so rules will NPE!!!
		tx.getSubstitutionDrugList().get(0).setStatus(StatusType.INACTIVE);
		saveTxToXMLFile(tx, "NoActiveDrug.xml");
		ErxTransaction txo=rp.processDrugSelection(tx);
		printRulesFired();
		Assert.assertEquals(ResultType.MANUAL,txo.getCompletionStatus());
	}
	
	@Test
	/**
	 * When a note is added to the prescription go manual
	 * 
	 *  Check Notes Found on Prescription
	 */
	public void testNotEmptyNoteOnPrescription(){
		System.out.println("testNotEmptyNoteOnPrescription");
		ErxTransaction tx= DrugFactory.buildERXtxTabletDrugPrecription();
		tx.getPrescription().setNotes("You should verify person");
		saveTxToXMLFile(tx, "NotEmptyNoteOnPrescription.xml");
		ErxTransaction txo=rp.processDrugSelection(tx);
		printRulesFired();
		Assert.assertEquals(ResultType.MANUAL,txo.getCompletionStatus());
	}
	
	@Test
	/**
	 * Set Manual - Original Date after Current Date
	 * 
	 * name:	Manual when original date after current date
	 */
	public void notPrescriptionAfterTransactionDate(){
		System.out.println("notPrescriptionAfterTransactionDate");
		ErxTransaction tx= DrugFactory.buildERXtxTabletDrugPrecription();
		// prescription is set by the factory to 8/11
		tx.setTransactionDateTime(DateUtilities.makeDate(2014, 8, 5));
		saveTxToXMLFile(tx, "notPrescriptionAfterTransactionDate.xml");
		ErxTransaction txo=rp.processDrugSelection(tx);
		printRulesFired();
		Assert.assertEquals(ResultType.MANUAL,txo.getCompletionStatus());
	}

	
	/**
	 * If the transmitted NDC does not have a GPI, the eRx must go to the manual process.
	 * 3.1.9
	 */
	@Test
	public void testEmptyGPI(){
		System.out.println("testEmptyGPI");
		ErxTransaction tx= DrugFactory.buildERXtxTabletDrugPrecription();
		tx.getSubstitutionDrugList().get(0).setGenericProductIdentifier("");
		saveTxToXMLFile(tx, "EmptyGPI.xml");
		ErxTransaction txo=rp.processDrugSelection(tx);
		printRulesFired();
		Assert.assertEquals(ResultType.MANUAL,txo.getCompletionStatus());
	}

   
}
