package com.walgreens.pharmacy.rules.ut;

import com.walgreens.pharmacy.drugselection.ut.TestDrugSelectionGoManual;
import com.walgreens.pharmacy.drugselection.ut.TestDrugSelectionPrepareCandidates;


/**
 * This class is used to execute one of unit test and be able to start the debugger to step thru the
 * working memory and rules
 * @author boyerje
 *
 */
public class RunToAuthorizeDebug {

	public static void main(String[] args) {

		TestDrugSelectionPrepareCandidates aTest = new TestDrugSelectionPrepareCandidates();
		aTest.testStorePreferredProduct();
	}
	//	TestSelectionRuleManual aTest= new TestSelectionRuleManual();
	//aTest.testTherapeuticdrugSelection();
	//TestDrugSelectionGoManual aTest = new TestDrugSelectionGoManual();
//	aTest.testControllingProduct();
//	VerifyDrugExclusion aTest = new VerifyDrugExclusion();
//	aTest.testDrugGPIExcludedButNDCoverridden();
}
