package com.walgreens.pharmacy.drugselection.ut;

import org.junit.Assert;
import org.junit.Test;

import com.walgreens.pharmacy.rules.ErxTransaction;
import com.walgreens.pharmacy.rules.ResultType;
import com.walgreens.pharmacy.rules.ut.factory.DrugFactory;
import com.walgreens.pharmacy.ut.BaseTest;
import com.walgreens.pharmacy.ut.DateUtilities;

/**
 * Test all the rules of the post processing once the drug is selected
 * @author boyerje
 *
 */
public class TestPostProcessingOnceDrugSelected extends BaseTest {

	@Test
	/**
	 * Validate the requirement:
	 * FR# 0.02  If the prescription 'Original Date' is less than the current date minus 'Max Days to Fill' 
	 * for the selected product or greater than the current date the system must exclude the eRx from the automation process
	 */
	public void testPrescriptionDateIsSameDay() {
		System.out.println("testPrescriptionDateIsSameDay");
		ErxTransaction tx= DrugFactory.buildERXtxTabletDrugPrecription();
		
		// prescription is set by the factory to 8/11
		tx.setTransactionDateTime(DateUtilities.makeDate(2014, 8, 11));
		ErxTransaction txo=rp.processDrugSelection(tx);
		printRulesFired();
		Assert.assertEquals(ResultType.IN_PROGRESS,txo.getCompletionStatus());
	}
	
	@Test
	public void testPrescriptionDateIsBeforeToday() {
		System.out.println("testPrescriptionDateIsBeforeToday");
		ErxTransaction tx= DrugFactory.buildERXtxTabletDrugPrecription();
		
		// prescription is set by the factory to 8/11
		tx.setTransactionDateTime(DateUtilities.makeDate(2014, 8, 9));
		ErxTransaction txo=rp.processDrugSelection(tx);
		printRulesFired();
		Assert.assertEquals(ResultType.MANUAL,txo.getCompletionStatus());
	}

}
